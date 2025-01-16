package com.example.mallxx.controller;

import com.example.mallxx.entity.Cart_details;
import com.example.mallxx.entity.Shopping_carts;
import com.example.mallxx.mapper.Cart_detailsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/cart_details")
public class Cart_detailsController {
    private final Cart_detailsMapper cart_detailsMapper;

    public Cart_detailsController(Cart_detailsMapper cart_detailsMapper) {
        this.cart_detailsMapper = cart_detailsMapper;
    }

    //根据Shopping_carts.cart_id获取用户购物车具体信息 成功
    @GetMapping("/get_cart_details_list")
    public ResponseEntity<List<Cart_details>> getCart_detailsList(@Param("cart_id") String cart_id) {
        List<Cart_details> cart_details_list=cart_detailsMapper.findCart_detailsByCart_id(Integer.parseInt(cart_id));
        System.out.println(cart_details_list);
        return ResponseEntity.ok(cart_details_list);
    }

    //成功
    @GetMapping("/get_cart_details_one")
    public ResponseEntity<Cart_details> getCart_detailsOne(@Param("cart_details_id") String cart_details_id) {
        Cart_details cart_details_one=cart_detailsMapper.findCart_detailsByCart_details_id(Integer.parseInt(cart_details_id));
        System.out.println(cart_details_one);
        return ResponseEntity.ok(cart_details_one);
    }

    //成功
    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteCart_details(@Param("cart_id") String cart_id) {
        cart_detailsMapper.DeleteByCart_id(Integer.parseInt(cart_id));
        return ResponseEntity.noContent().build();
    }

    //成功
    @DeleteMapping("deleteOne")
    public ResponseEntity<Void> deleteCart_details_one(@Param("cart_details_id") String cart_details_id) {
        cart_detailsMapper.DeleteByCart_details_id(Integer.parseInt(cart_details_id));
        return ResponseEntity.noContent().build();
    }

    //成功
    @PostMapping("insert")
    public ResponseEntity<Void> insertCart_details(@RequestBody Cart_details cart_details) {
        cart_details.setCreate_data(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(cart_details);
        cart_detailsMapper.Insert(cart_details);
        return ResponseEntity.noContent().build();
    }

    //成功
    @PutMapping("/edit")
    public ResponseEntity<Void> editCart_details(@Param("cart_detail_id") String cart_detail_id, @Param("quantity") String quantity) {
        Cart_details cart_details=cart_detailsMapper.findCart_detailsByCart_details_id(Integer.parseInt(cart_detail_id));
        cart_details.setQuantity(Integer.parseInt(quantity));
        cart_details.setCart_details_id(Integer.parseInt(cart_detail_id));
        System.out.println(cart_details);
        cart_detailsMapper.Update(cart_details);
        return ResponseEntity.noContent().build();
    }



}
