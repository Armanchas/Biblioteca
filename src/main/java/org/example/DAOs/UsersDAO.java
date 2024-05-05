package org.example.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.Book;
import org.example.User;
import org.example.DatabaseConnection;
public class UsersDAO {
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                users.add(new User(id, username, password, role));
            }
        }

        return users;
    }

    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (username, password) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());

            stmt.executeUpdate();
        }
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET username = ?, password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, user.getId());

            stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM Users WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String role = rs.getString("role");

                return new User(id, username, password, role);
            }
        }

        return null;
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String role = rs.getString("role");

                return new User(id, username, password, role);
            }
        }

        return null;
    }

    public List<Book> getBorrowHistory(int userId) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.author, b.category, b.quantity FROM Books b " +
                     "JOIN BorrowedBooks bh ON b.id = bh.book_id " +
                     "WHERE bh.user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");

                books.add(new Book(id, title, author, category, quantity));
            }
        }

        return books;
    }

    public List<Book> getReservedBooks(int userId) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.title, b.author, b.category, b.quantity FROM Books b " +
                     "JOIN ReservedBooks rb ON b.id = rb.book_id " +
                     "WHERE rb.user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                String category = rs.getString("category");
                int quantity = rs.getInt("quantity");

                books.add(new Book(id, title, author, category, quantity));
            }
        }

        return books;
    }

    public void borrowBook(int userId, int bookId) throws SQLException {
        String sql = "INSERT INTO BorrowedBooks (user_id, book_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);

            stmt.executeUpdate();
        }
    }

    public void returnBook(int userId, int bookId) throws SQLException {
        String sql = "DELETE FROM BorrowedBooks WHERE user_id = ? AND book_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);

            stmt.executeUpdate();
        }
    }

    public void reserveBook(int userId, int bookId) throws SQLException {
        String sql = "INSERT INTO ReservedBooks (user_id, book_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);

            stmt.executeUpdate();
        }
    }

    public String getUserPassword(String username) throws SQLException {
        String sql = "SELECT password FROM Users WHERE username = ?";
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

    public String getUserRole(String username) throws SQLException {
        String sql = "SELECT role FROM Users WHERE username = ?";
        String role = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
            }
        }

        return role;
    }

    public int getUserId(String username) throws SQLException {
        String sql = "SELECT id FROM Users WHERE username = ?";
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

    public String getUsername(int id) throws SQLException {
        String sql = "SELECT username FROM Users WHERE id = ?";
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

    public boolean isValidLogin(String username, String password) {
        try {
            String dbPassword = getUserPassword(username);

            return dbPassword != null && dbPassword.equals(password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}