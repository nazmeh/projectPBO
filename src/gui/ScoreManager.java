// ScoreManager.java
package gui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScoreManager implements Runnable {

    private final ScoreListener scoreListener;
    private final Connection connection; // Dependency: koneksi database

    public ScoreManager(ScoreListener scoreListener, Connection connection) {
        this.scoreListener = scoreListener;
        this.connection = connection;
    }

    @Override
    public void run() {
        // Logic untuk memperbarui skor secara berkala
        while (true) {
            // Mendapatkan skor dari database menggunakan connection yang diinjeksi
            try (Connection connection = this.connection) {
                String query = "SELECT username, score FROM score JOIN users ON score.player_id = users.id ORDER BY score DESC LIMIT 1";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            String playerName = resultSet.getString("username");
                            int score = resultSet.getInt("score");
                            scoreListener.updateScore(playerName, score);
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Menunggu beberapa saat sebelum pembaruan berikutnya
            try {
                Thread.sleep(5000); // Gantilah dengan durasi yang diinginkan (dalam milidetik)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // ...
}
