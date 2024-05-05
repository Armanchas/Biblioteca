package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    //se que es pesima idea tener credenciales en el codigo, pero en esta ocasion no importa porque no se le va a dar un uso real
    private static final String url = "jdbc:postgresql://localhost/library";
    private static final String user = "postgres";
    private static final String password = "password";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}