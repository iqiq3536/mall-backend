package com.example.mallxx.entity;

public class CartDetailsWithProduct {
    private int user_id;
    private String username;
    private int cart_details_id;
    private int cart_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private int merchant_id;
    private String product_name;
    private String img_url;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getCart_id() {
        return cart_id;
    }

    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }

    public int getCart_details_id() {
        return cart_details_id;
    }

    public void setCart_details_id(int cart_details_id) {
        this.cart_details_id = cart_details_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "CartDetailsWithProduct{" +
                "userId=" + user_id +
                ", username='" + username + '\'' +
                ", cart_details_id=" + cart_details_id +
                ", cart_id=" + cart_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                ", merchant_id=" + merchant_id +
                ", name='" + product_name + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
