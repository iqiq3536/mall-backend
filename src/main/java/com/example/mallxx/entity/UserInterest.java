package com.example.mallxx.entity;
public class UserInterest {
    private int userId;         // 用户ID （外键）
    private int tagId;          // 标签ID （外键）
    private int interestScore;  // 用户对该标签的兴趣得分 （1-5）

    // 默认构造函数
    public UserInterest() {}

    // 全参构造函数
    public UserInterest(int userId, int tagId, int interestScore) {
        this.userId = userId;
        this.tagId = tagId;
        this.interestScore = interestScore;
    }

    // Getter 和 Setter 方法
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public int getTagId() { return tagId; }
    public void setTagId(int tagId) { this.tagId = tagId; }

    public int getInterestScore() { return interestScore; }
    public void setInterestScore(int interestScore) { this.interestScore = interestScore; }
}