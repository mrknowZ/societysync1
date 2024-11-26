package cpedsoc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/societysync"; // Update with your database name
    private static final String USER = "root"; // Update with your MySQL username
    private static final String PASSWORD = ""; // Update with your MySQL password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database");
        }
    }
}
