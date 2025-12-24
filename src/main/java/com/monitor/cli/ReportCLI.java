package com.monitor.cli;

import java.sql.*;
import java.util.Scanner;

public class ReportCLI {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Show Top 5 Errors");

        int choice = scanner.nextInt();

        if (choice == 1) {
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/monitoring",
                    "root", "password");
                 Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery(
                        "SELECT message, COUNT(*) cnt " +
                                "FROM incidents GROUP BY message " +
                                "ORDER BY cnt DESC LIMIT 5");

                while (rs.next()) {
                    System.out.println(
                            rs.getString("message") +
                                    " | Count: " + rs.getInt("cnt"));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
