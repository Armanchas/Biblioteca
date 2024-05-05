package org.example.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.example.Administrator;
import org.example.DatabaseConnection;

public class AdministratorsDAO {

    public List<Administrator> getAllAdministrators() throws SQLException {
        List<Administrator> administrators = new ArrayList<>();
        String sql = "SELECT * FROM Administrators";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");

                administrators.add(new Administrator(id, username, password));
            }
        }

        return administrators;
    }
    
    public Administrator getAdminByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Administrators WHERE username = ?";
        Administrator administrator = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");

                administrator = new Administrator(id, username, password);
            }
        }

        return administrator;
    }

    public void createAdministrator(Administrator administrator) throws SQLException {
        String sql = "INSERT INTO Administrators (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, administrator.getUsername());
            stmt.setString(2, administrator.getPassword());

            stmt.executeUpdate();
        }
    }

    public void updateAdministrator(Administrator administrator) throws SQLException {
        String sql = "UPDATE Administrators SET username = ?, password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, administrator.getUsername());
            stmt.setString(2, administrator.getPassword());
            stmt.setInt(3, administrator.getId());

            stmt.executeUpdate();
        }
    }

    public String getAdminPassword(String username) throws SQLException {
        String sql = "SELECT password FROM Administrators WHERE username = ?";
        String password = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                password = rs.getString("password");
            }
        }

        return password;
    }

    public int getAdminId(String username) throws SQLException {
        String sql = "SELECT id FROM Administrators WHERE username = ?";
        int id = -1;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
        }

        return id;
    }

    public String getAdminUsername(int id) throws SQLException {
        String sql = "SELECT username FROM Administrators WHERE id = ?";
        String username = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                username = rs.getString("username");
            }
        }

        return username;
    }

    public boolean isValidAdminLogin(String username, String password) {
        try {
            String correctPassword = getAdminPassword(username);
            return correctPassword != null && correctPassword.equals(password);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}