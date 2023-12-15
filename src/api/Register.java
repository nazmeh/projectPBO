package api;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register {
    public static void register(String username, String password) throws SQLException {
        String checkQuery = "SELECT COUNT(*) FROM USERS WHERE username = '" + username + "'";
        try (Statement checkStmt = JDBC.client.createStatement()) {
            ResultSet resultSet = checkStmt.executeQuery(checkQuery);
            resultSet.next();
            int userCount = resultSet.getInt(1);
            if (userCount > 0) {
                System.out.println("User already exists.");
                JOptionPane.showMessageDialog(null, "User already exists");
                return;
            }

            String query = "INSERT INTO USERS (username, password) VALUES ('" + username + "', '" + password + "')";
            try (Statement stmt = JDBC.client.createStatement()) {
                int rowsAffected = stmt.executeUpdate(query);
                if (rowsAffected > 0) {
                    System.out.println("User successfully inserted.");
                    JOptionPane.showMessageDialog(null, "Register Success");
                } else {
                    System.out.println("Failed to insert user.");
                    JOptionPane.showMessageDialog(null, "Register failed");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
