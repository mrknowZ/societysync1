package cpedsoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPage extends JFrame {
    public LoginPage() {
        setTitle("Login");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JButton btnLogin = new JButton("Login");
        JButton btnSignup = new JButton("Signup");

        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnSignup);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, txtEmail.getText());
                    stmt.setString(2, new String(txtPassword.getPassword()));

                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        dispose();
                        new DashboardPage();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid credentials");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SignupPage();
            }
        });

        add(panel);
        setVisible(true);
    }
}
