package cpedsoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SignupPage extends JFrame {
    public SignupPage() {
        setTitle("Signup");
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2));

        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        JPasswordField txtPassword = new JPasswordField();

        JButton btnSignup = new JButton("Signup");
        JButton btnBack = new JButton("Back");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnSignup);
        panel.add(btnBack);

        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, txtName.getText());
                    stmt.setString(2, txtEmail.getText());
                    stmt.setString(3, new String(txtPassword.getPassword()));

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Account created successfully");
                    dispose();
                    new LoginPage();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginPage();
            }
        });

        add(panel);
        setVisible(true);
    }
}
