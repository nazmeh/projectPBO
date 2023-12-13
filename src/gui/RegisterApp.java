package gui;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Objects;

public class RegisterApp extends JFrame {

    JPanel panel;

    public RegisterApp() {
        super("Brick Breaker Register");
    }

    public void makePanel() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(300, 500));
        panel.setBackground(Color.decode("#323437"));


        JLabel tittle = new JLabel("Register");
        tittle.setForeground(Color.decode("#e2b714"));
        tittle.setFont(new Font("Poppins", Font.BOLD, 25));
        tittle.setPreferredSize(new Dimension(300, 30));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 1));
        panel.add(tittle);

        JLabel desc = new JLabel("Please, enter your detail");
        desc.setPreferredSize(new Dimension(300, 30));
        desc.setFont(new Font("Poppins", Font.BOLD, 14));
        desc.setForeground(Color.WHITE);
        panel.add(desc);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setPreferredSize(new Dimension(300, 35));
        usernameLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        usernameLabel.setForeground(Color.decode("#e2b714"));
        panel.add(usernameLabel);

        JTextField username = new JTextField();
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

        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(300, 30));
        password.setBackground(Color.decode("#212224"));
        password.setForeground(Color.decode("#e2b714"));
        password.setFont(new Font("Poppins", Font.PLAIN, 12));
        password.setCaretColor(Color.decode("#e2b714"));
        password.setBorder(new MatteBorder(0, 10, 0, 10, Color.decode("#212224")));

        panel.add(password);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setPreferredSize(new Dimension(300, 35));
        confirmPasswordLabel.setFont(new Font("Poppins", Font.BOLD, 12));
        confirmPasswordLabel.setForeground(Color.decode("#e2b714"));
        panel.add(confirmPasswordLabel);

        JPasswordField confirmPassword = new JPasswordField();
        confirmPassword.setPreferredSize(new Dimension(300, 30));
        confirmPassword.setBackground(Color.decode("#212224"));
        confirmPassword.setForeground(Color.decode("#e2b714"));
        confirmPassword.setFont(new Font("Poppins", Font.PLAIN, 12));
        confirmPassword.setCaretColor(Color.decode("#e2b714"));
        confirmPassword.setBorder(new MatteBorder(0, 10, 0, 10, Color.decode("#212224")));

        panel.add(confirmPassword);

        JPanel gap = new JPanel();
        gap.setBackground(Color.decode("#323437"));
        gap.setPreferredSize(new Dimension(300, 25));
        panel.add(gap);

        JButton registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(300, 40));
        registerButton.setBackground(Color.decode("#e2b714"));
        registerButton.setFont(new Font("Poppins", Font.BOLD, 16));
        registerButton.setForeground(Color.decode("#323437"));
        registerButton.setBorder(null);
        panel.add(registerButton);

        JPanel bottomDetail = new JPanel();
        bottomDetail.setBackground(null);
        bottomDetail.setPreferredSize(new Dimension(300, 30));
        bottomDetail.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));

        JLabel asking = new JLabel("already have an account?");
        asking.setFont(new Font("Poppins", Font.PLAIN, 10));
        asking.setForeground(Color.WHITE);

        JButton loginButton = new JButton("login here");
        loginButton.setFont(new Font("Poppins", Font.BOLD, 10));
        loginButton.setForeground(Color.decode("#e2b714"));
        loginButton.setBackground(null);
        loginButton.setBorderPainted(false);

        bottomDetail.add(asking);
        bottomDetail.add(loginButton);

        panel.add(bottomDetail);

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 50));
        add(panel);

        registerButton.addActionListener(ActionEvent -> {
            String usernameInput = username.getText();
            String passwordInput = new String(password.getPassword());
            String confirmPasswordInput = new String(confirmPassword.getPassword());
            registerAction(usernameInput, passwordInput, confirmPasswordInput);
        });

        username.addActionListener(ActionEvent -> {
            String usernameInput = username.getText();
            String passwordInput = new String(password.getPassword());
            String confirmPasswordInput = new String(confirmPassword.getPassword());
            registerAction(usernameInput, passwordInput, confirmPasswordInput);
        });

        password.addActionListener(ActionEvent -> {
            String usernameInput = username.getText();
            String passwordInput = new String(password.getPassword());
            String confirmPasswordInput = new String(confirmPassword.getPassword());
            registerAction(usernameInput, passwordInput, confirmPasswordInput);
        });

        confirmPassword.addActionListener(ActionEvent -> {
            String usernameInput = username.getText();
            String passwordInput = new String(password.getPassword());
            String confirmPasswordInput = new String(confirmPassword.getPassword());
            registerAction(usernameInput, passwordInput, confirmPasswordInput);
        });

        loginButton.addActionListener(ActionEvent -> {
            LoginApp login = new LoginApp();
            login.showLogin();
            dispose();
        });

    }

    private void registerAction(String username, String password, String confirmPassword) {
        if (Objects.equals(password, confirmPassword)) {
            try {
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all the field");
                }else{
                    api.Register.register(username, password);
                    LoginApp login = new LoginApp();
                    login.showLogin();
                    dispose();
                }
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, exception.getMessage());
                exception.printStackTrace();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Password not match");
        }
    }

    protected void showRegister() {
        makePanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("assets/icon.png");
        Image image = icon.getImage();
//        setUndecorated(true);


        setIconImage(image);
        getContentPane().setBackground(Color.decode("#323437"));
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
