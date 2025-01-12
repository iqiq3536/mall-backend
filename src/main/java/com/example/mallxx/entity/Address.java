package com.example.mallxx.entity;


public class Address {
    private Integer address_id;
    private String province;
    private String city;
    private String county;
    private String detailedAddress;
    private Integer user_id;

    // Getters and Setters
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    // Getter and Setter for city
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    // Getter and Setter for county
    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    // Getter and Setter for detailedAddress
    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    // Getter and Setter for userId
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer userId) {
        this.user_id = userId;
    }
    public Integer getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Integer userId) {
        this.address_id = address_id;
    }
}
