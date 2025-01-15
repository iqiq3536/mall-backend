package com.example.mallxx.entity;

public class ProductDetail {

    private int id;          // 自增主键
    private int productId;   // 产品ID
    private String url;      // 图片路径
    private int order;       // 图片顺序

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    // 可选：toString 方法（方便打印调试）
    @Override
    public String toString() {
        return "ProductDetail{" +
                "id=" + id +
                ", productId=" + productId +
                ", url='" + url + '\'' +
                ", order=" + order +
                '}';
    }
}
