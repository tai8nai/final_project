package org.example.sports;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;
import java.util.*;

public class CsvParser {
    public static List<SportObject> parse(String csvResource, Map<String, Region> regionMap) throws Exception {
        List<SportObject> result = new ArrayList<>();
        CSVReader reader = new CSVReader(new InputStreamReader(
                CsvParser.class.getClassLoader().getResourceAsStream(csvResource), "windows-1251"));
        String[] nextLine;
        reader.readNext(); // skip header
        int id = 1;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine.length < 5) continue;
            String name = nextLine[1].trim();
            String type = nextLine[2].trim();
            String address = nextLine[3].trim();
            String dateCommissioned = nextLine[4].trim();
            if (name.isEmpty() || type.isEmpty() || address.isEmpty()) continue;

            String regionName = getRegionFromAddress(address);
            if (regionName == null) regionName = "Неизвестно";

            Region region = regionMap.computeIfAbsent(regionName, rn -> new Region(rn));
            result.add(new SportObject(id++, name, type, address, dateCommissioned, region));
        }
        return result;
    }

    // Упрощённое извлечение региона из адреса
    private static String getRegionFromAddress(String address) {
        if (address.contains("Москва")) return "Москва и Московская область";
        if (address.contains("Московская обл")) return "Москва и Московская область";
        if (address.contains("Московская область")) return "Москва и Московская область";
        // Добавьте другие правила по необходимости!
        String[] regions = {
            "Санкт-Петербург", "Новосибирская область", "Краснодарский край", "Свердловская область",
            "Республика Татарстан", "Челябинская область", "Ростовская область", "Башкортостан", "Самарская область"
        };
        for (String region : regions) {
            if (address.contains(region)) return region;
        }
        return null;
    }
}