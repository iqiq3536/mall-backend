package com.example.mallxx.entity;

import java.util.Date;

import java.util.Date;

public class Recommendation {
    private int recommendationId;  // 推荐记录ID （主键）
    private int userId;            // 用户ID （外键）
    private int productId;         // 商品ID （外键）
    private Date recommendedAt;    // 推荐时间
    private String actionTaken;    // 用户对推荐商品的行为 ('view' 表示浏览， 'purchase' 表示购买)

    // 默认构造函数
    public Recommendation() {}

    // 全参构造函数
    public Recommendation(int recommendationId, int userId, int productId, Date recommendedAt, String actionTaken) {
        this.recommendationId = recommendationId;
        this.userId = userId;
        this.productId = productId;
        this.recommendedAt = recommendedAt;
        this.actionTaken = actionTaken;
    }

    // Getter 和 Setter 方法
    public int getRecommendationId() { return recommendationId; }
    public void setRecommendationId(int recommendationId) { this.recommendationId = recommendationId; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public Date getRecommendedAt() { return recommendedAt; }
    public void setRecommendedAt(Date recommendedAt) { this.recommendedAt = recommendedAt; }

    public String getActionTaken() { return actionTaken; }
    public void setActionTaken(String actionTaken) { this.actionTaken = actionTaken; }
}