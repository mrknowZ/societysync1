package cpedsoc;

import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class DashboardPage extends JFrame {

    public DashboardPage() {
        setTitle("Dashboard");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Stats Panel
        JPanel statsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        statsPanel.add(createStatLabel("Total Members: " + MemberStatistics.getTotalMembers()));
        statsPanel.add(createStatLabel("Presidents: " + MemberStatistics.getCountByPosition("President")));
        statsPanel.add(createStatLabel("Heads: " + MemberStatistics.getCountByPosition("Head")));
        statsPanel.add(createStatLabel("Co-Heads: " + MemberStatistics.getCountByPosition("Co-Head")));
        statsPanel.add(createStatLabel("Executives: " + MemberStatistics.getCountByPosition("Executive")));
        statsPanel.add(createStatLabel("General Members: " + MemberStatistics.getCountByPosition("General Member")));

        panel.add(statsPanel, BorderLayout.NORTH);

        // Pie Chart Panel
        JPanel chartPanel = new JPanel(new BorderLayout());
        chartPanel.add(createPieChart(), BorderLayout.CENTER);
        panel.add(chartPanel, BorderLayout.CENTER);

        // Navigation Buttons
        JButton btnAddMember = new JButton("Add Member");
        JButton btnSearch = new JButton("Search Members");

        btnAddMember.addActionListener(e -> {
            dispose();
            new AddMemberPage();
        });

        btnSearch.addActionListener(e -> {
            dispose();
            new SearchPage();
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAddMember);
        buttonPanel.add(btnSearch);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private JLabel createStatLabel(String text) {
        JLabel label = new JLabel(text, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        return label;
    }

    private JPanel createPieChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Presidents", MemberStatistics.getCountByPosition("President"));
        dataset.setValue("Heads", MemberStatistics.getCountByPosition("Head"));
        dataset.setValue("Co-Heads", MemberStatistics.getCountByPosition("Co-Head"));
        dataset.setValue("Executives", MemberStatistics.getCountByPosition("Executive"));
        dataset.setValue("General Members", MemberStatistics.getCountByPosition("General Member"));

        JFreeChart chart = ChartFactory.createPieChart("Member Distribution", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        return new ChartPanel(chart);
    }
}
