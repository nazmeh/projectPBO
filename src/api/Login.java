package api;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login {

    public static boolean isLogin = false;

    public static void login(String username, String password) throws SQLException {
        String query = "SELECT DISTINCT * FROM USERS WHERE username = '" + username + "'";
        try (Statement stmt = JDBC.client.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                JDBC.setUser_id(rs.getString("id"));
                String passDB = rs.getString("password");
                if (password.equals(passDB)) {
                    System.out.println("Login success.");
                    JOptionPane.showMessageDialog(null, "Login success");
                    isLogin = true;
                } else {
                    System.out.println("Login failed.");
                    JOptionPane.showMessageDialog(null, "Login failed");
                }
            } else {
                System.out.println("Login failed.");
                JOptionPane.showMessageDialog(null, "Login failed");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
