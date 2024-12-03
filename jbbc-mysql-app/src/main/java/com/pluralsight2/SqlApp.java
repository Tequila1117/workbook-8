package com.pluralsight2;
import java.sql.*;
import java.util.Scanner;

public class SqlApp {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/dealership";
        String user = "root";
        String password = "yearup";
        boolean running = true;

        Scanner scanner = new Scanner(System.in);

        while (running) {
            System.out.print("Enter a SQL query: ");
            String command = scanner.nextLine();

            if (command.equalsIgnoreCase("exit")) {
                running = false; // Exit loop if user enters "exit"
            } else {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    Statement statement = connection.createStatement();

                    if (command.trim().toUpperCase().startsWith("SELECT")) {
                        // If it's a SELECT query
                        ResultSet resultSet = statement.executeQuery(command);
                        while (resultSet.next()) {
                            String make = resultSet.getString("MAKE");
                            System.out.println(make);
                        }
                    } else {
                        // If it's not a SELECT query ( ex. INSERT, UPDATE, DELETE)
                        int rowsAffected = statement.executeUpdate(command);
                        System.out.println("Rows affected: " + rowsAffected);
                    }
                } catch (SQLException e) {
                    e.printStackTrace(); // Print detailed error if needed
                    System.out.println("Error executing query. Please check your SQL syntax.");
                }
            }
        }

        scanner.close(); // Close scanner after use
    }
}