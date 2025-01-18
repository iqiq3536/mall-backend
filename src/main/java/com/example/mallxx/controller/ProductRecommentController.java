package com.example.mallxx.controller;

import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.ProductExamMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import com.example.mallxx.mapper.ProductExamMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mallxx.Service.UserInterestProductService;
import com.example.mallxx.mapper.ProductMapper;

import java.util.Map;

@RestController
@RequestMapping("/api/ProductRecomment")

public class ProductRecommentController {
    private final UserInterestProductService UserInterestProductService;
    private final ProductMapper ProductMapper;
    private final ProductExamMapper ProductExamMapper;
    public ProductRecommentController(UserInterestProductService UserInterestProductService, ProductMapper ProductMapper, ProductExamMapper ProductExamMapper) {
        this.UserInterestProductService = UserInterestProductService;
        this.ProductMapper = ProductMapper;
        this.ProductExamMapper = ProductExamMapper;
    }

    /**
     * 调用UserInterestProductService.getRecommendedProducts来获取要推荐的商品id
     * 在获取商品详情，返回商品列表
     */
    @GetMapping("/List")
    public ResponseEntity<List<Product>> List(@CookieValue(value = "user_id", required = false)String User_id) {
        int user_id = Integer.parseInt(User_id);
        System.out.println("//**/*/*/////////"+user_id+"user_id"+"-------------------------");
        List<Integer> Productids = UserInterestProductService.getRecommendedProducts(user_id);
        //System.out.println(Productids);
        //List<Integer> productIds = Arrays.asList(42, 43, 44, 45, 46, 47,48,49,50,51);
        List<Product> Products = ProductMapper.findByIdList(Productids);

        return ResponseEntity.ok(Products);
    }
    /**
     * 通过cookie的id,和product的id来添加历史记录
     */
    @PostMapping("/addhis")
    public ResponseEntity<String> addhis(@CookieValue(value = "user_id", required = false)String User_id,@RequestBody Map<String, Integer> requestBody
    )
        {
            int user_id = Integer.parseInt(User_id);
            System.out.println("//**/*/*/////////"+user_id+"user_id"+"-------------------------");
            int product_id = requestBody.get("product_id");
            System.out.println("//**/*/*/////////"+product_id+"product_id"+"-------------------------");
            ProductExamMapper.addhis(user_id,product_id);
            return ResponseEntity.ok("添加成功");
    }

}