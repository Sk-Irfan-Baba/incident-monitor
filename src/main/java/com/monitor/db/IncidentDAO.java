package com.monitor.db;

import java.sql.*;

public class IncidentDAO {

    private static final String URL =
            "jdbc:mysql://localhost:3306/monitoring";
    private static final String USER = "root";
    private static final String PASS = "password"; // change this

    public static void save(String type, String message) {
        String sql = "INSERT INTO incidents (type, message) VALUES (?, ?)";

        try {
            // ✅ ADD THIS LINE HERE
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, type);
            ps.setString(2, message);
            ps.executeUpdate();

            ps.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
