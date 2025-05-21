package org.example.sports;

public class Region {
    private int id;
    private String name;

    public Region(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Region(String name) { this.name = name; }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
}