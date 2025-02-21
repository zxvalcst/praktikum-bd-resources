package org.example;

import java.sql.*;

public class SimpleDatabaseConnection {
    public static void main(String[] args) {
        // Create database connection with DriverManager
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            if (conn != null) {
                System.out.println("Connection established");

                // Do things once connection is established
                // In this case, print the PostgreSQL version
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT VERSION()");
                if (rs.next()) {
                    System.out.println(rs.getString(1));
                }
            } else {
                System.out.println("Connection failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}