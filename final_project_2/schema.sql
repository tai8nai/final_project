CREATE TABLE region (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL UNIQUE
);

CREATE TABLE sport_object (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT,
    type TEXT,
    address TEXT,
    date_commissioned TEXT,
    region_id INTEGER,
    FOREIGN KEY (region_id) REFERENCES region(id)
);