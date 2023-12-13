package gui;

import api.Login;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginApp extends JFrame {

    private JPanel panel;
    public JTextField username;
    private JPasswordField password;
    private boolean loginSuccess = false;

    public LoginApp() {
        super("Brick Breaker Login");
        this.username = new JTextField();
        this.password = new JPasswordField();
    }

    public void makePanel() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 300));
        panel.setBackground(Color.decode("#323437"));

        JLabel title = new JLabel("Login");
        title.setFont(new Font("Poppins", Font.BOLD, 25));
        title.setPreferredSize(new Dimension(300, 30));
        title.setForeground(Color.decode("#e2b714"));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));
        panel.add(title);

        JLabel desc = new JLabel("Please, Fill Your Biodata!");
        desc.setPreferredSize(new Dimension(300, 30));
        desc.setFont(new Font("Poppins", Font.BOLD, 14));
        desc.setForeground(Color.WHITE);
        panel.add(desc);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setPreferredSize(new Dimension(300, 35));
        usernameLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        usernameLabel.setForeground(Color.decode("#e2b714"));
        panel.add(usernameLabel);

        username.setPreferredSize(new Dimension(300, 30));
        username.setBackground(Color.decode("#212224"));
        username.setForeground(Color.decode("#e2b714"));
        username.setFont(new Font("Poppins", Font.PLAIN, 12));
        username.setCaretColor(Color.decode("#e2b714"));
        username.setBorder(new MatteBorder(0, 10, 0, 10, Color.decode("#212224")));
        panel.add(username);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setPreferredSize(new Dimension(300, 35));
        passwordLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        passwordLabel.setForeground(Color.decode("#e2b714"));
        panel.add(passwordLabel);

        password.setPreferredSize(new Dimension(300, 30));
        password.setBackground(Color.decode("#212224"));
        password.setForeground(Color.decode("#e2b714"));
        password.setFont(new Font("Poppins", Font.PLAIN, 12));
        password.setCaretColor(Color.decode("#e2b714"));
        password.setBorder(new MatteBorder(0, 10, 0, 10, Color.decode("#212224")));

        panel.add(password);

        JPanel gap = new JPanel();
        gap.setBackground(Color.decode("#323437"));
        gap.setPreferredSize(new Dimension(300, 25));
        panel.add(gap);

        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(300, 40));
        loginButton.setBackground(Color.decode("#e2b714"));
        loginButton.setFont(new Font("Poppins", Font.BOLD, 16));
        loginButton.setForeground(Color.decode("#323437"));
        loginButton.setBorder(null);
        panel.add(loginButton);

        JPanel bottomDetail = new JPanel();
        bottomDetail.setBackground(null);
        bottomDetail.setPreferredSize(new Dimension(300, 30));
        bottomDetail.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JLabel asking = new JLabel("not have an account?");
        asking.setFont(new Font("Poppins", Font.PLAIN, 10));
        asking.setForeground(Color.WHITE);

        JButton registerButton = new JButton("register here");
        registerButton.setFont(new Font("Poppins", Font.BOLD, 10));
        registerButton.setForeground(Color.decode("#e2b714"));
        registerButton.setBackground(null);
        registerButton.setBorderPainted(false);

        bottomDetail.add(asking);
        bottomDetail.add(registerButton);

        panel.add(bottomDetail);

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 65));
        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = username.getText();
                String passwordInput = new String(password.getPassword());
                try {
                    Login.login(usernameInput, passwordInput);
                    if (Login.isLogin) {
                        showMenu();
                        dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(LoginApp.this, "Error during login: " + ex.getMessage(),
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        username.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = username.getText();
                String passwordInput = new String(password.getPassword());
                try {
                    Login.login(usernameInput, passwordInput);
                    if (Login.isLogin) {
                        showMenu();
                        dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(LoginApp.this, "Error during login: " + ex.getMessage(),
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        password.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usernameInput = username.getText();
                String passwordInput = new String(password.getPassword());
                try {
                    Login.login(usernameInput, passwordInput);
                    if (Login.isLogin) {
                        showMenu();
                        dispose();
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(LoginApp.this, "Error during login: " + ex.getMessage(),
                            "Login Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterApp register = new RegisterApp();
                register.showRegister();
                dispose();
            }
        });
    }

    private void showMenu() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public void showLogin() {
        makePanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.decode("#323437"));
        ImageIcon icon = new ImageIcon("assets/icon.png");
        Image image = icon.getImage();

        setIconImage(image);

        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginApp loginApp = new LoginApp();
            loginApp.showLogin();
        });
    }
}
