package com.example.mallxx.entity;

public class Cart_details {
    private int cart_details_id;
    private int cart_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private String create_data;
    private int merchant_id;


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

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public String getCreate_data() {
        return create_data;
    }

    public void setCreate_data(String create_data) {
        this.create_data = create_data;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    @Override
    public String toString() {
        return "Cart_details{" +
                "cart_details_id=" + cart_details_id +
                ", cart_id=" + cart_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                ", creat_data='" + create_data + '\'' +
                ", merchant_id=" + merchant_id +
                '}';
    }
}
