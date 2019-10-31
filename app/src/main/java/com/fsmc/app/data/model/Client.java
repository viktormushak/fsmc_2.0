package com.fsmc.app.data.model;

public class Client {

    private int rate;
    private int hashId;
    private String name;
    private String company;
    private String address;
    private double totalScore;

    public void setRate(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public int getHashId() {
        return hashId;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public double getTotalScore() {
        return totalScore;
    }
}
