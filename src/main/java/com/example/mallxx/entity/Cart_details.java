package com.example.mallxx.entity;

public class Cart_details {
    private int cart_details_id;
    private int cart_id;
    private int product_id;
    private int quantity;
    private int unit_price;
    private String creat_data;

    public int getCart_details_id() {
        return cart_details_id;
    }
    public void setCart_details_id(int cart_details_id) {
        this.cart_details_id = cart_details_id;
    }
    public int getCart_id() {
        return cart_id;
    }
    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getUnit_price() {
        return unit_price;
    }
    public void setUnit_price(int unit_price) {
        this.unit_price = unit_price;
    }
    public String getCreat_data() {
        return creat_data;
    }
    public void setCreat_data(String creat_data) {
        this.creat_data = creat_data;
    }


}
