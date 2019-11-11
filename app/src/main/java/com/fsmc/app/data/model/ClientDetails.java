package com.fsmc.app.data.model;

import java.util.List;

@SuppressWarnings("unused")
public class ClientDetails {

    public static final ClientDetails EMPTY = new ClientDetails();

    private String name;
    private double totalScore;
    private List<Address> addresses;
    private List<Brand> brands;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(double totalScore) {
        this.totalScore = totalScore;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
