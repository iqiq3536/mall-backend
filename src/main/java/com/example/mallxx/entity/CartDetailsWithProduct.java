package com.example.mallxx.entity;

public class CartDetailsWithProduct {
    private int userId;
    private String username;
    private int cartDetailsId;
    private int cartId;
    private int productId;
    private int quantity;
    private double unitPrice;
    private int merchantId;
    private String productName;
    private String imgUrl;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCartDetailsId() {
        return cartDetailsId;
    }

    public void setCartDetailsId(int cartDetailsId) {
        this.cartDetailsId = cartDetailsId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "CartDetailsWithProduct{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", cartDetailsId=" + cartDetailsId +
                ", cartId=" + cartId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", merchantId=" + merchantId +
                ", productName='" + productName + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
