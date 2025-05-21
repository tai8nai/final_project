package org.example.sports;

public class SportObject {
    private int id;
    private String name;
    private String type;
    private String address;
    private String dateCommissioned;
    private Region region;

    public SportObject(int id, String name, String type, String address, String dateCommissioned, Region region) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.dateCommissioned = dateCommissioned;
        this.region = region;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getAddress() { return address; }
    public String getDateCommissioned() { return dateCommissioned; }
    public Region getRegion() { return region; }
}