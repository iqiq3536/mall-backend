package com.example.mallxx.entity;

import java.util.Date;

public class UserPurchaseHistory {
    private int purchaseId;
    private int userId;
    private int productId;
    private int quantity;
    private Date purchaseDate;

    // Constructors
    public UserPurchaseHistory() {}

    public UserPurchaseHistory(int purchaseId, int userId, int productId, int quantity, Date purchaseDate) {
        this.purchaseId = purchaseId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters
    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "UserPurchaseHistory{" +
                "purchaseId=" + purchaseId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
