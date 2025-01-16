package com.example.mallxx.controller;

import com.example.mallxx.DTO.RatingDTO;
import com.example.mallxx.mapper.UserRatingMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    private final UserRatingMapper userRatingMapper;

    public RatingController(UserRatingMapper userRatingMapper) {
        this.userRatingMapper = userRatingMapper;
    }


    @GetMapping("/{productId}")
    public List<RatingDTO> getRatings(@PathVariable int productId) {
        return userRatingMapper.findRatingsByProductId(productId);
    }
}


