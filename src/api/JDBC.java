package api;

import java.sql.*;

public class JDBC {

    static Connection client;
    static String user_id;

    public JDBC() {
        try {
            String url = "jdbc:mysql://localhost:3306/brick_breaker?user=root&password=";
            client = DriverManager.getConnection(url);
            System.out.println("Connection success.");
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace(); // Menampilkan informasi kesalahan ke konsol
        }
    }

    public static void setUser_id(String user_id) {
        JDBC.user_id = user_id;
    }

    public static String getUser_id() {
        return user_id;
    }

    public static ResultSet getScore() throws SQLException {
        String query = "SELECT username, score FROM score JOIN users ON score.player_id = users.id ORDER BY score DESC";
        PreparedStatement preparedStatement = client.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    // Metode untuk menambah skor ke database
    public static void addScore(String player_id, int score) throws SQLException {
        // Mendapatkan user_id berdasarkan username
        String userId = getUserIdByUsername(player_id);
        if (userId != null) {
            String query = "INSERT INTO score (player_id, score) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = client.prepareStatement(query)) {
                preparedStatement.setString(1, player_id);
                preparedStatement.setInt(2, score);
                preparedStatement.executeUpdate();
            }
        }
    }

    // Metode untuk mendapatkan user_id berdasarkan username
    private static String getUserIdByUsername(String username) throws SQLException {
        String query = "SELECT id FROM users WHERE username = ?";
        try (PreparedStatement preparedStatement = client.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("id");
                }
            }
        }
        return null;
    }
}
