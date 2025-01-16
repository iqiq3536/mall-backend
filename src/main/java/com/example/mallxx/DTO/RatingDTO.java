package com.example.mallxx.DTO;

import javax.xml.crypto.Data;

import java.time.LocalDateTime;
import java.util.Date;

public class RatingDTO {
    private int rating_id;
    private String username;
    private int rating;
    private String review;
    private LocalDateTime rating_date;

    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public LocalDateTime getRating_date() {
        return rating_date;
    }

    public void setRating_date(LocalDateTime rating_date) {
        this.rating_date = rating_date;
    }
}
