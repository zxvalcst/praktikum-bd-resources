package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PooledDatabaseConnection {
    public static void main(String[] args) {
        // Get a connection from the HikariCPDataSource
        try {
            Connection conn = HikariCPDataSource.getConnection();
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