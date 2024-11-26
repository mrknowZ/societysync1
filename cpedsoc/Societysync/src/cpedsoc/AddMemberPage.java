package cpedsoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class AddMemberPage extends JFrame {
    public AddMemberPage() {
        setTitle("Add Member");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2));

        JLabel lblName = new JLabel("Name:");
        JTextField txtName = new JTextField();
        JLabel lblTeam = new JLabel("Team:");
        JTextField txtTeam = new JTextField();
        JLabel lblPosition = new JLabel("Position:");
        JTextField txtPosition = new JTextField();
        JLabel lblJoiningDate = new JLabel("Joining Date (YYYY-MM-DD):");
        JTextField txtJoiningDate = new JTextField();
        JLabel lblRegNo = new JLabel("Registration Number:");
        JTextField txtRegNo = new JTextField();
        JLabel lblDepartment = new JLabel("Department:");
        JTextField txtDepartment = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();

        JButton btnAdd = new JButton("Add Member");
        JButton btnBack = new JButton("Back");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblTeam);
        panel.add(txtTeam);
        panel.add(lblPosition);
        panel.add(txtPosition);
        panel.add(lblJoiningDate);
        panel.add(txtJoiningDate);
        panel.add(lblRegNo);
        panel.add(txtRegNo);
        panel.add(lblDepartment);
        panel.add(txtDepartment);
        panel.add(lblEmail);
        panel.add(txtEmail);
        panel.add(btnAdd);
        panel.add(btnBack);

        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "INSERT INTO members (name, team, position, joining_date, registration_number, department, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1, txtName.getText());
                    stmt.setString(2, txtTeam.getText());
                    stmt.setString(3, txtPosition.getText());
                    stmt.setString(4, txtJoiningDate.getText());
                    stmt.setString(5, txtRegNo.getText());
                    stmt.setString(6, txtDepartment.getText());
                    stmt.setString(7, txtEmail.getText());

                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Member added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnBack.addActionListener(e -> {
            dispose();
            new DashboardPage();
        });

        add(panel);
        setVisible(true);
    }
}
