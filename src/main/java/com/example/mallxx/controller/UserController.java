package com.example.mallxx.controller;


import com.example.mallxx.dao.SellerDao;
import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.Seller;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.SellerMapper;
import com.example.mallxx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final SellerMapper sellerMapper;

    public UserController(SellerMapper sellerMapper) {
        this.sellerMapper = sellerMapper;
    }
    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }

    @GetMapping("/findAll")
    public List<Seller> findAllUser() {
        return sellerMapper.findAll();
    }

    @GetMapping("/userlist")
    public ResponseEntity<List<User>> getAllUser() {
        List<Product> User = UserMapper.findAll();
        return ResponseEntity.ok(User);
    }
}