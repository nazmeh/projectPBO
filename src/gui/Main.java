package gui;

import api.JDBC;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Gunakan SwingUtilities.invokeLater untuk memastikan operasi-operasi Swing
        // berada pada thread pengantaran acara
        SwingUtilities.invokeLater(() -> {
            try {
                // Inisialisasi objek JDBC
                JDBC db = new JDBC();

                // Tampilkan login
                LoginApp login = new LoginApp();
                login.showLogin();

                // Jika login berhasil
                if (login.isLoginSuccess()) {
                    // Tampilkan permainan setelah login
                    showGameplay();
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Tangani exception secara sesuai, tampilkan pesan kepada pengguna, atau catat
                // kesalahan
            }
        });
    }

    private static void showGameplay() {
        // Tampilkan permainan
        JFrame gameFrame = new JFrame();
        Gameplay gamePlay = new Gameplay();

        gameFrame.setBounds(10, 10, 700, 600);
        gameFrame.setTitle("Breakout Ball");
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.add(gamePlay);
        // Tambahkan baris ini untuk menempatkan frame di tengah layar
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
