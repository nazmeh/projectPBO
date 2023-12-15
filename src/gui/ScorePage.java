package gui;

import api.JDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScorePage extends JFrame {

    public ScorePage() {
        initComponents();
    }

    private void initComponents() {
        JTextArea scoreTextArea = new JTextArea();
        scoreTextArea.setEditable(false);

        try {
            // Mendapatkan ResultSet dari API JDBC
            ResultSet resultSet = JDBC.getScore();

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

            while (resultSet.next()) {
                String playerName = resultSet.getString("username");
                int score = resultSet.getInt("score");

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

    public void showScore() {
        setVisible(true);
    }

}
