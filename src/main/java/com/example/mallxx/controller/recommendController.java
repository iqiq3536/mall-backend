package com.example.mallxx.controller;

import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.UserMapper;
import com.example.mallxx.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mallxx.Service.RandomNumberService;
import com.example.mallxx.Service.UserInterestProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/recommend")
public class recommendController {
    private final UserMapper userMapper;
    private final RandomNumberService randomNumberService;
    private final ProductMapper ProductMapper;
    private final UserInterestProductService UserInterestProductService;
    public recommendController(UserMapper userMapper, RandomNumberService randomNumberService , ProductMapper ProductMapper ,UserInterestProductService UserInterestProductService) {
        this.userMapper = userMapper;
        this.randomNumberService = randomNumberService;
        this.UserInterestProductService = UserInterestProductService;
        this.ProductMapper = ProductMapper;
    }

    /**
     * 利用user_id返回要推荐的商品
     * @param request
     * @return
     */
    @PostMapping("/firstproduct") // 使用POST方法
    public ResponseEntity<List<Product>> firstProduct(@RequestBody User request) {
        // 获取用户ID
        int userId = request.getUser_id();

        // 获取推荐的商品ID列表
        List<Integer> recommendedProductIds = UserInterestProductService.getRecommendedProducts(userId);

        // 限制推荐的商品ID列表最多为前20个（如果少于20个，则返回实际数量）
        List<Integer> limitedRecommendedProductIds = recommendedProductIds.stream()
                .limit(20)
                .collect(Collectors.toList());

        // 根据商品ID列表获取商品详情
        List<Product> recommendedProducts = ProductMapper.findByIdList(limitedRecommendedProductIds);

        // 返回HTTP响应
        return ResponseEntity.ok(recommendedProducts);
    }


}
