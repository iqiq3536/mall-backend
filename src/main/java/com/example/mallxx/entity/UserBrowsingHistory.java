package com.example.mallxx.entity;


import java.util.Date;

public class UserBrowsingHistory {
    private int history_Id;
    private int user_id;
    private int product_id;
    private Date view_date;
    private int seconds;

    // Constructors
    public UserBrowsingHistory() {}

    public UserBrowsingHistory(int historyId, int userId, int product_id, Date view_date, int seconds) {
        this.history_Id = historyId;
        this.user_id = userId;
        this.product_id = product_id;
        this.view_date = view_date;
        this.seconds = seconds;
    }

    // Getters and Setters
    public int getHistory_Id() {
        return history_Id;
    }

    public void setHistory_Id(int history_Id) {
        this.history_Id = history_Id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProductId(int product_id) {
        this.product_id = product_id;
    }

    public Date getViewDate() {
        return view_date;
    }

    public void setView_date(Date view_date) {
        this.view_date = view_date;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "UserBrowsingHistory{" +
                "history_Id=" + history_Id +
                ", user_id=" + user_id +
                ", productId=" + product_id +
                ", viewDate=" + view_date +
                ", seconds=" + seconds +
                '}';
    }
}


