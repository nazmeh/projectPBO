package gui;

import api.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScorePage extends JFrame implements ScoreListener {

    private JPanel panel;

    public ScorePage() {
        initComponents();
    }

    private void initComponents() {
        try {
            // Mendapatkan ResultSet dari API JDBC
            ResultSet topScores = JDBC.getTopScores(5);
            // Menampilkan skor dalam JPanel
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 10));
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel leaderboardLabel = new JLabel("SCOREBOARD");
            leaderboardLabel.setFont(new Font("Poppins", Font.BOLD, 12));
            leaderboardLabel.setForeground(Color.white);
            leaderboardLabel.setBackground(Color.decode("#2c3e50"));
            leaderboardLabel.setOpaque(true);
            leaderboardLabel.setBounds(10, 10, 120, 25);
            panel.add(leaderboardLabel);

            while (topScores.next()) {
                String playerName = topScores.getString("username");
                int score = topScores.getInt("score");

                JLabel usernameLabel = new JLabel(playerName + " Score: " + score);
                panel.add(usernameLabel);
            }
            scrollPane.setViewportView(panel);
            JFrame frame = new JFrame();
            frame.setSize(250, 200);
            frame.getContentPane().add(scrollPane);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to retrieve scores. Please check the database connection.");
        }

    }

    @Override
    public void updateScore(String playerName, int score) {
        // Update nilai skor pada UI atau lakukan tindakan lain
        SwingUtilities.invokeLater(() -> {
            JLabel usernameLabel = new JLabel(playerName + " Score: " + score);
            panel.add(usernameLabel);
            // Mungkin perlu memperbarui tampilan skor pada layar
            // atau melakukan operasi UI lainnya.
        });
    }

    public void showScore() {
        setVisible(true);
    }

}
