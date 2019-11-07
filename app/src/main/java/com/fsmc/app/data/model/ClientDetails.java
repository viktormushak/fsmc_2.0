package com.fsmc.app.data.model;

import java.util.Collections;
import java.util.List;

public class ClientDetails {

    public static final ClientDetails EMPTY =
            new ClientDetails("", Collections.emptyList(), Collections.emptyList());
    private String name;
    private double totalScore;
    private List<Address> addresses;
    private List<ClientDetails.Brand> brands;

    private ClientDetails(String name, List<Address> addresses, List<Brand> brands) {
        this.name = name;
        this.addresses = addresses;
        this.brands = brands;
    }

    public static class Brand {
        private String name;
        private double quality;

        public Brand(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public double getQuality() {
            return quality;
        }
    }

    public static class Address {
        private String address;
        private String region;
        private String city;
        private String street;

        public Address(String address) {
            this.address = address;
            this.region = "Kyivska";
            this.city = "Kyiv";
            this.street = "Bazhana, 8";

        }

        public String getAddress() {
            return address;
        }

        public String getRegion() {
            return region;
        }

        public String getCity() {
            return city;
        }

        public String getStreet() {
            return street;
        }

        public boolean isFullAddressCorrect(){
            return region != null && city != null && street != null;
        }
    }

    public String getName() {
        return name;
    }

    public double getTotalScore() {
        return totalScore;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public List<Brand> getBrands() {
        return brands;
    }
}
