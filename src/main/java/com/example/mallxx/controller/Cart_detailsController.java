package com.example.mallxx.controller;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Product;
import com.example.mallxx.entity.Shopping_carts;
import com.example.mallxx.mapper.Cart_detailsMapper;
import com.example.mallxx.mapper.ProductMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart_details")
public class Cart_detailsController {
    private final Cart_detailsMapper cart_detailsMapper;

    public Cart_detailsController(Cart_detailsMapper cart_detailsMapper) {
        this.cart_detailsMapper = cart_detailsMapper;
    }
    // 获取购物车详情列表
    @GetMapping("/get_cart_details_list")
    public ResponseEntity<List<Cart_details>> getCart_detailsList(@RequestBody Shopping_carts shopping_carts) {
        List<Cart_details> cart_details_list=cart_detailsMapper.findCart_detailsByCart_id(shopping_carts);

        return ResponseEntity.ok(cart_details_list);
    }
    // 获取购物车详情
    @GetMapping("/get_cart_details_one")
    public ResponseEntity<Cart_details> getCart_detailsOne(@RequestBody Cart_details cart_details) {
        Cart_details cart_details_one=cart_detailsMapper.findCart_detailsByCart_details_id(cart_details);
        return ResponseEntity.ok(cart_details_one);
    }

    // 删除购物车详情
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteCart_details(@RequestBody Shopping_carts shopping_carts) {
        cart_detailsMapper.DeleteByCart_id(shopping_carts);
        return ResponseEntity.noContent().build();
    }
    // 删除购物车详情
    @DeleteMapping("deleteOne")
    public ResponseEntity<Void> deleteCart_details(@RequestBody Cart_details cart_details) {
        cart_detailsMapper.DeleteByCart_details_id(cart_details);
        return ResponseEntity.noContent().build();
    }

    // 添加购物车详情
    @PostMapping("insert")
    public ResponseEntity<Void> insertCart_details(@RequestBody Cart_details cart_details) {
        cart_detailsMapper.Insert(cart_details);
        return ResponseEntity.noContent().build();
    }
    // 修改购物车详情
    @PutMapping("/edit")
    public ResponseEntity<List<Cart_details>> editCart_details(@RequestBody Shopping_carts shopping_carts, @RequestBody Cart_details cart_details) {
        cart_detailsMapper.Update(cart_details);
        List<Cart_details> cart_details_list=cart_detailsMapper.findCart_detailsByCart_id(shopping_carts);
        return ResponseEntity.ok(cart_details_list);
    }



}
