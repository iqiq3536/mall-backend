package com.example.mallxx.entity;

import java.util.Date;


public class UserRating {
    private int ratingId;      // 评分记录ID （主键）
    private int userId;        // 用户ID （外键）
    private int productId;     // 商品ID （外键）
    private int rating;        // 评分 （1-5）
    private String review;     // 评价内容
    private Date ratingDate;   // 评分时间

    // 默认构造函数
    public UserRating() {}

    // 全参构造函数
    public UserRating(int ratingId, int userId, int productId, int rating, String review, Date ratingDate) {
        this.ratingId = ratingId;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.review = review;
        this.ratingDate = ratingDate;
    }

    // Getter 和 Setter 方法
    public int getRatingId() { return ratingId; }
    public void setRatingId(int ratingId) { this.ratingId = ratingId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }

    public Date getRatingDate() { return ratingDate; }
    public void setRatingDate(Date ratingDate) { this.ratingDate = ratingDate; }
}