package gui;

import javax.swing.*;
// import javax.swing.Timer;

import api.JDBC;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gameplay extends JPanel implements GameplayAction, KeyListener, ActionListener {
	private boolean play = false;
	public static int score = 0;
	private int totalBricks = 21;
	private int delay = 10;
	private int playerX = 310;
	private int ballposX = 120;
	private int ballposY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	private MapGenerator map;

	public Gameplay() {
		map = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		startBallMovementThread();
	}

	public static Connection getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/brick_breaker?user=root&password=";
			Connection client = DriverManager.getConnection(url);
			System.out.println("Connection success.");
			return client;
		} catch (SQLException e) {
			System.out.println("Connection failure.");
			System.exit(1);
		}
		return null;
	}

	public static void setScore() {
		String name = JDBC.getUser_id();
		Connection con = getConnection();
		String query = "UPDATE score SET score = ? WHERE player_id = ? ";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setInt(1, score);
			stmt.setString(2, name);
			int affected = stmt.executeUpdate();
			if (affected > 0) {
				System.err.println("Upadate succes");
			} else {
				System.err.println("Upadate fail");
			}
		} catch (Exception e) {

		}
	}

	public static int getCurrentScore(String playerName) {
		int currentScore = 0;
		Connection con = getConnection();
		String query = "SELECT score FROM score WHERE player_id = ?";
		try (PreparedStatement stmt = con.prepareStatement(query)) {
			stmt.setString(1, playerName);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					currentScore = rs.getInt("score");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return currentScore;
	}

	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		// drawing map
		map.draw((Graphics2D) g);

		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);

		// the score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" + score, 590, 30);

		// the paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);

		// the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);

		// when you won the game
		if (totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won", 260, 300);

			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press (Enter) to Restart", 230, 350);
			g.drawString("Press (Esc) to Exit", 250, 400);
		}

		// when you lose the game
		if (ballposY > 570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over, Scores: " + score, 190, 300);

			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press (Enter) to Restart", 230, 350);
			g.drawString("Press (Esc) to Exit", 250, 400);
		}

		g.dispose();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600) {
				playerX = 600;
			} else {
				moveRight();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				ballposX = 120;
				ballposY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				map = new MapGenerator(3, 7);

				repaint();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (!play) {
				Menu menu = new Menu();
				menu.showMenu();
				((JFrame) SwingUtilities.getWindowAncestor(Gameplay.this)).dispose();
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// Implement keyReleased method
	}

	public void keyTyped(KeyEvent e) {
		// Implement keyTyped method
	}

	@Override
	public void moveRight() {
		play = true;
		playerX += 20;
	}

	@Override
	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	public void actionPerformed(ActionEvent e) {
		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir = -2;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8))) {
				ballYdir = -ballYdir;
			}

			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							score += 5;
							totalBricks--;
							setScore();

							if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}

							break A;
						}
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;

			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) {
				ballXdir = -ballXdir;
			}

			repaint();
		}
	}

	private void startBallMovementThread() {
		Thread ballMovementThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					moveBall();

					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		ballMovementThread.start();
	}

	private void moveBall() {
		if (play) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir = -2;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 70, 550, 30, 8))) {
				ballYdir = -ballYdir;
				ballXdir = ballXdir + 1;
			} else if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX + 30, 550, 40, 8))) {
				ballYdir = -ballYdir;
			}

			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							score += 5;
							totalBricks--;
							setScore();

							if (ballposX + 19 <= brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}

							break A;
						}
					}
				}
			}

			ballposX += ballXdir;
			ballposY += ballYdir;

			if (ballposX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballposY < 0) {
				ballYdir = -ballYdir;
			}
			if (ballposX > 670) {
				ballXdir = -ballXdir;
			}

			repaint();
		}
	}
}
