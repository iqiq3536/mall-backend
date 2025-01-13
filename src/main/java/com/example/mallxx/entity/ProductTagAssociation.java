package com.example.mallxx.entity;

public class ProductTagAssociation {
    private int productId;
    private int tagId;

    // Constructors
    public ProductTagAssociation() {}

    public ProductTagAssociation(int productId, int tagId) {
        this.productId = productId;
        this.tagId = tagId;
    }

    // Getters and Setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ProductTagAssociation{" +
                "productId=" + productId +
                ", tagId=" + tagId +
                '}';
    }
}
