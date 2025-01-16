package com.example.mallxx.entity;

public class Order_details {
    private int order_detail_id;
    private int order_id;
    private int product_id;
    private int quantity;
    private double unit_price;
    private double total_price;
    private String create_at;
    private String order_detail_status;//订单状态

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public double getTotal_price() {
        return total_price;
    }

    public String getCreate_at() {
        return create_at;
    }

    public String getOrder_detail_status() {
        return order_detail_status;
    }

    public String getShipping_status() {
        return shipping_status;
    }

    public String getLast_update_at() {
        return last_update_at;
    }

    public int getMerchant_id() {
        return merchant_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public String getOrderNo() {
        return orderNo;
    }

    private String shipping_status;//配送状态
    private String last_update_at;
    private int merchant_id;
    private int user_id;
    private String shipping_address;
    private String orderNo;

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setLast_update_at(String last_update_at) {
        this.last_update_at = last_update_at;
    }

    public void setShipping_status(String shipping_status) {
        this.shipping_status = shipping_status;
    }

    public void setOrder_detail_status(String order_detail_status) {
        this.order_detail_status = order_detail_status;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    @Override
    public String toString() {
        return "Order_details{" +
                "order_detail_id=" + order_detail_id +
                ", order_id=" + order_id +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                ", unit_price=" + unit_price +
                ", total_price=" + total_price +
                ", create_at='" + create_at + '\'' +
                ", order_detail_status='" + order_detail_status + '\'' +
                ", shipping_status='" + shipping_status + '\'' +
                ", last_update_at='" + last_update_at + '\'' +
                ", merchant_id=" + merchant_id +
                ", user_id=" + user_id +
                ", shipping_address='" + shipping_address + '\'' +
                ", orderNo='" + orderNo + '\'' +
                '}';
    }
}
