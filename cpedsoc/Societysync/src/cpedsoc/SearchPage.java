package cpedsoc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchPage extends JFrame {
    public SearchPage() {
        setTitle("Search Members");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel searchPanel = new JPanel(new GridLayout(1, 2));
        JTextField txtSearch = new JTextField();
        JButton btnSearch = new JButton("Search");

        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        JTable resultTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Name", "Reg No", "Department", "Position"});
        resultTable.setModel(model);

        JScrollPane scrollPane = new JScrollPane(resultTable);

        JButton btnBack = new JButton("Back");

        setLayout(new BorderLayout());
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0); // Clear previous results
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String sql = "SELECT name, registration_number, department, position FROM members WHERE name LIKE ? OR registration_number LIKE ? OR department LIKE ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    String searchTerm = "%" + txtSearch.getText() + "%";
                    stmt.setString(1, searchTerm);
                    stmt.setString(2, searchTerm);
                    stmt.setString(3, searchTerm);

                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        model.addRow(new Object[]{
                                rs.getString("name"),
                                rs.getString("registration_number"),
                                rs.getString("department"),
                                rs.getString("position")
                        });
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnBack.addActionListener(e -> {
            dispose();
            new DashboardPage();
        });

        setVisible(true);
    }
}
