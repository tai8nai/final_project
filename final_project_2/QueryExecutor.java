public class QueryExecutor {
    public static void printTopRegions(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT region.name, COUNT(sport_object.id) as count FROM sport_object JOIN region ON sport_object.region_id = region.id GROUP BY region.name ORDER BY count DESC LIMIT 3"
        );
        System.out.println("Топ-3 региона по количеству объектов спорта:");
        while (rs.next()) {
            System.out.println(rs.getString("name") + ": " + rs.getInt("count"));
        }
    }

    public static void printAverage(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(
            "SELECT AVG(region_count) as avg_count FROM (SELECT COUNT(sport_object.id) as region_count FROM sport_object GROUP BY region_id)"
        );
        if (rs.next()) {
            System.out.println("Среднее количество объектов спорта в регионах: " + rs.getDouble("avg_count"));
        }
    }
}