package com.fsmc.app.data.model;

@SuppressWarnings("unused")
public class Address {

    public static final Address EMPTY = new Address();
    private String region;
    private String city;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
