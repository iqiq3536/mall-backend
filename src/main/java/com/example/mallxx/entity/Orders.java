package com.example.mallxx.entity;

public class Orders {
    private int order_id;
    private int user_id;
    private String order_status;//订单状态
    private String create_at;
    private double total_amount;
    private String payment_method;
    private String shipping_address;


    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getOrder_status() {
        return order_status;
    }
    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }
    public String getCreate_at() {
        return create_at;
    }
    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }
    public double getTotal_amount() {
        return total_amount;
    }
    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }
    public String getPayment_method() {
        return payment_method;
    }
    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
    public String getShipping_address() {
        return shipping_address;
    }
    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

}
