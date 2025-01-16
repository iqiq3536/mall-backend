package com.example.mallxx.controller;

import com.example.mallxx.entity.Shopping_carts;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.Shopping_cartsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class Shopping_cartsController {
    private final Shopping_cartsMapper shopping_cartsMapper;

    public Shopping_cartsController(Shopping_cartsMapper shopping_cartsMapper) {
        this.shopping_cartsMapper = shopping_cartsMapper;
    }

    //成功
    @PostMapping("/InsertShopping_carts")
    public void AddShopping_carts(@CookieValue(value = "user_id", required = false)String user_id) {
        System.out.println(user_id);
        Shopping_carts shopping_carts=shopping_cartsMapper.findByUser_id(Integer.parseInt(user_id));
        if(shopping_carts==null){
            shopping_cartsMapper.add(Integer.parseInt(user_id));
        }
    }

    //成功
    @PostMapping("/SelectShopping_carts")
    public int selectShopping_carts(@CookieValue(value = "user_id", required = false)String user_id) {
        int id=Integer.parseInt(user_id);
        Shopping_carts shopping_carts=shopping_cartsMapper.findByUser_id(id);
        System.out.println(shopping_carts);
        return shopping_carts.getCart_id();
    }
}
