package com.example.mallxx.entity;

public class Shopping_carts {
    private int cart_id;
    private int user_id;

    public int getCart_id() {
        return cart_id;
    }
    public void setCart_id(int cart_id) {
        this.cart_id = cart_id;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Shopping_carts{" +
                "cart_id=" + cart_id +
                ", user_id=" + user_id +
                '}';
    }
}
