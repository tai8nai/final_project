package org.example.sports;

import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        System.setOut(new java.io.PrintStream(System.out, true, "windows-1251"));
        // 1. Парсинг CSV
        Map<String, Region> regionMap = new HashMap<>();
        List<SportObject> objects = CsvParser.parse("sports.csv", regionMap);

        // 2. Работа с SQLite
        DatabaseManager db = new DatabaseManager("sports.db");
        db.createTables();
        for (SportObject so : objects) db.insertSportObject(so);

        // 3. SQL-запросы и вывод в консоль
        QueryExecutor.printAverage(db.getConnection());
        QueryExecutor.printTopRegions(db.getConnection());

        // 4. Визуализация
        ChartUtils.createChart(db.getConnection());

        db.close();
    }
}