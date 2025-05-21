package org.example.sports;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChartUtils {
    public static void createChart(Connection conn) throws SQLException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT region.name, COUNT(sport_object.id) as count FROM sport_object JOIN region ON sport_object.region_id = region.id GROUP BY region.name"
        );
        while (rs.next()) {
            dataset.addValue(rs.getInt("count"), "Объекты спорта", rs.getString("name"));
        }
        JFreeChart barChart = ChartFactory.createBarChart(
            "Количество объектов спорта по регионам",
            "Регион", "Количество объектов", dataset
        );
        JFrame frame = new JFrame("Гистограмма: объекты спорта по регионам");
        frame.setContentPane(new ChartPanel(barChart));
        frame.setSize(1200, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}