package com.example.mallxx.entity;

public class ProductTagAssociation {
    private int product_id;
    private int tag_id;

    @Override
    public String toString() {
        return "ProductTagAssociation{" +
                "product_id=" + product_id +
                ", tag_id=" + tag_id +
                '}';
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getTag_id() {
        return tag_id;
    }

    public void setTag_id(int tag_id) {
        this.tag_id = tag_id;
    }
}
