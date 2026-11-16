
package com.medicalstock.ui;

import javax.swing.*;
import java.awt.*;

import com.medicalstock.dao.AuthDAO;
import com.medicalstock.model.Admin;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private AuthDAO authDAO;

    public LoginFrame() {

        authDAO = new AuthDAO();

        setTitle("Medical Stock and Expiry Monitor");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel("MEDICAL STOCK MONITOR");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(25, 80, 150));
        title.setBounds(65, 35, 330, 30);
        panel.add(title);

        JLabel subtitle = new JLabel("Admin Login");
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setBounds(165, 75, 150, 25);
        panel.add(subtitle);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(65, 125, 100, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(165, 125, 200, 30);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(65, 175, 100, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(165, 175, 200, 30);
        panel.add(passwordField);

        JButton loginButton = new JButton("LOGIN");
        loginButton.setBounds(165, 235, 120, 35);
        loginButton.setBackground(new Color(25, 80, 150));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        panel.add(loginButton);

        loginButton.addActionListener(e -> login());

        add(panel);
    }

    private void login() {

        String username = usernameField.getText().trim();

        String password = new String(
                passwordField.getPassword()
        );

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter username and password."
            );

            return;
        }

        Admin admin = authDAO.login(username, password);

        if (admin != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Login successful!"
            );

            DashboardFrame dashboard =
                    new DashboardFrame(admin);

            dashboard.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password."
            );
        }
    }
}