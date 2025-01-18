package com.example.mallxx.entity;
public class UserInterest {
    private int user_id;         // 用户ID （外键）
    private int tag_id;          // 标签ID （外键）
    private int interest_score;  // 用户对该标签的兴趣得分

    public int getInterest_score() {
        return interest_score;
    }

    public void setInterest_score(int interest_score) {
        this.interest_score = interest_score;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }

    @Override
    public String toString() {
        return "UserInterest{" +
                "user_id=" + user_id +
                ", tag_id=" + tag_id +
                ", interest_score=" + interest_score +
                '}';
    }
}