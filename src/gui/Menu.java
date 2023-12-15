package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        initComponents();
        showMenu();
    }

    private void initComponents() {
        JButton playButton = new JButton("Play");
        JButton scoreButton = new JButton("Score");
        JButton exitButton = new JButton("Exit");

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGameplay();
            }
        });

        playButton.setBackground(Color.decode("#e2b714"));
        playButton.setForeground(Color.black);
        scoreButton.setBackground(Color.decode("#e2b714"));
        scoreButton.setForeground(Color.black);
        exitButton.setBackground(Color.decode("#e2b714"));
        exitButton.setForeground(Color.black);

        JPanel panel = new JPanel(new GridLayout(3, 1, 0, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(100, 40, 100, 40));
        panel.add(playButton);
        panel.add(scoreButton);
        panel.add(exitButton);
        panel.setBackground(Color.black);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().setBackground(Color.black);

        setTitle("Menu");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        scoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScorePage score = new ScorePage();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void showMenu() {
        setVisible(true);
    }

    private void showGameplay() {
        JFrame gameFrame = new JFrame();
        Gameplay gamePlay = new Gameplay();
        gameFrame.setBounds(10, 10, 700, 600);
        gameFrame.setTitle("Breakout Ball");
        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.add(gamePlay);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }
}
