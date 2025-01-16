package com.example.mallxx.controller;

import com.example.mallxx.entity.Shopping_carts;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.Shopping_cartsMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Shopping_cartsController {
    private final Shopping_cartsMapper shopping_cartsMapper;

    public Shopping_cartsController(Shopping_cartsMapper shopping_cartsMapper) {
        this.shopping_cartsMapper = shopping_cartsMapper;
    }

    @PostMapping("/InsertShopping_carts")
    public void AddShopping_carts(@RequestBody User user) {
        //System.out.println(user.getUser_id()+"******+++++++++++------------");
        shopping_cartsMapper.add(user);
    }

    @PostMapping("/SelectShopping_carts")
    public Shopping_carts selectShopping_carts(@RequestBody User user) {
        Shopping_carts shopping_carts=shopping_cartsMapper.findByUser_id(user);
        return shopping_carts;
    }
}
