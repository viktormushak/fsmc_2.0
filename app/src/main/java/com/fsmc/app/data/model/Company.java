package com.fsmc.app.data.model;

@SuppressWarnings("unused")
public class Company {

    private String name;
    private long lastUpdate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
