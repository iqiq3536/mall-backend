package com.example.mallxx.controller;


import com.example.mallxx.entity.Product;
import com.example.mallxx.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class TestController {
    private final ProductMapper productMapper;
    public TestController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @RequestMapping("/api/test")
    public List<Product> getAllProducts() {
        List<Product> products = productMapper.findAll();

        return products;
    }
    @RequestMapping("/api/search")
    public List<Product> searchProducts(@RequestParam("keyword") String keyword) {
            // 调用 Mapper 搜索
            return productMapper.searchByKeyword(keyword);

    }

}
