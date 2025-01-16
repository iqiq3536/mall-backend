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

    /**
     * 通过user_id获取family_id，再通过family_id获取所有user_id,再获取所有user_id的购物车信息
     * @param user_id
     */
    /*@PostMapping("/getCart")
    public ResponseEntity<List<Shopping_carts>> getCart(@CookieValue(value = "user_id", required = false)String user_id) {
        int id=Integer.parseInt(user_id);

        Shopping_carts shopping_carts=shopping_cartsMapper.findByUser_id(id);
        System.out.println(shopping_carts);
        return ResponseEntity.ok(shopping_cartsMapper.findAll());
    }*/

    /**
     * findByUser_id2
     * 通过user_id获取购物车商品信息
     * @param user_id
     */
    @PostMapping("/getCart2")
    public ResponseEntity<List<Shopping_carts>> getCart2(@CookieValue(value = "user_id", required = false)String user_id) {
        int id=Integer.parseInt(user_id);
        System.out.println(id);
        return ResponseEntity.ok(shopping_cartsMapper.findByUser_id2(id));
    }

    /**
     * findByUser_id3
     * 通过user_id获取家庭购物车
     * @param user_id
     */
    @PostMapping("/getCart3")
    public ResponseEntity<List<Shopping_carts>> getCart3(@CookieValue(value = "user_id", required = false)String user_id) {
        int id=Integer.parseInt(user_id);
        System.out.println(id);
        return ResponseEntity.ok(shopping_cartsMapper.findByUser_id3(id));
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
