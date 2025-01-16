package com.example.mallxx.entity;

public class ProductTag {
    private int id;
    private String tag_name;
    private Category category;

    public enum Category { FLOWER, BIRD, FISH, PET, INSECT }

    // Constructors
    public ProductTag() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_name() {
        return tag_name;
    }

    public void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
