package gui;

import api.JDBC;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                JDBC db = new JDBC();

                LoginApp login = new LoginApp();
                login.showLogin();

                if (login.isLoginSuccess()) {
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
