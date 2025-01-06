package com.example.mallxx.controller;

import com.example.mallxx.dao.SellerDao;
import com.example.mallxx.entity.Seller;
import com.example.mallxx.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

}