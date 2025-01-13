package com.example.mallxx.entity;

public class ProductTag {
    private int id;
    private String name;
    private Category category;

    public enum Category { FLOWER, BIRD, FISH, PET, INSECT }

    // Constructors
    public ProductTag() {}

    public ProductTag(int id, String name, Category category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ProductTag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                '}';
    }
}
