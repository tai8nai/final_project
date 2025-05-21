package org.example.sports;

import java.sql.*;

public class DatabaseManager {
    private final Connection connection;

    public DatabaseManager(String dbPath) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
    }

    public void createTables() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS region (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT UNIQUE)");
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS sport_object (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, type TEXT, address TEXT, date_commissioned TEXT, region_id INTEGER, FOREIGN KEY(region_id) REFERENCES region(id))");
        stmt.close();
    }

    public int insertRegion(Region region) throws SQLException {
        PreparedStatement ps = connection.prepareStatement("INSERT OR IGNORE INTO region (name) VALUES (?)");
        ps.setString(1, region.getName());
        ps.executeUpdate();
        ps.close();

        PreparedStatement ps2 = connection.prepareStatement("SELECT id FROM region WHERE name=?");
        ps2.setString(1, region.getName());
        ResultSet rs = ps2.executeQuery();
        int id = rs.next() ? rs.getInt(1) : -1;
        region.setId(id);
        rs.close(); ps2.close();
        return id;
    }

    public void insertSportObject(SportObject so) throws SQLException {
        int regionId = insertRegion(so.getRegion());
        PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO sport_object (name, type, address, date_commissioned, region_id) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, so.getName());
        ps.setString(2, so.getType());
        ps.setString(3, so.getAddress());
        ps.setString(4, so.getDateCommissioned());
        ps.setInt(5, regionId);
        ps.executeUpdate();
        ps.close();
    }

    public Connection getConnection() { return connection; }

    public void close() throws SQLException { connection.close(); }
}