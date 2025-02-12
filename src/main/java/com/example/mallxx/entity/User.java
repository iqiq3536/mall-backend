package com.example.mallxx.entity;

public class User {
/*
UserId: 用户ID，主键。
username: 用户名。
password: 密码。
full_name: 用户全名。
gender: 性别。
contact_info: 联系信息。
family_id: 家庭ID。
family_name: 家庭名称。
*/

    private int user_id;
    private String username;
    private String password;
    private String full_name;
    private String gender;
    private String contact_info;
    private int family_id;
    private String family_name;

    public User() {}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getContact_info() {
        return contact_info;
    }

    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }


    public int getFamily_id() {
        return family_id;
    }

    public void setFamily_id(int family_id) {
        this.family_id = family_id;
    }


    public String getFamily_name() {
        return family_name;
    }

    public void setFamily_name(String family_name) {
        this.family_name = family_name;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", full_name='" + full_name + '\'' +
                ", gender='" + gender + '\'' +
                ", contact_info='" + contact_info + '\'' +
                ", family_id=" + family_id +
                ", family_name='" + family_name + '\'' +
                '}';
    }
}
