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
@RequestMapping("/api/user")
public class UserController {

    private final UserMapper UserMapper;

    public UserController(UserMapper UserMapper) {
        this.UserMapper = UserMapper;
    }
    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }

    @GetMapping("/findAll")
    public List<User> findAllUser() {
        return UserMapper.findAll();
    }

    @GetMapping("/userList")
    public ResponseEntity<List<User>> getAllUser() {
        List<User> User = UserMapper.findAll();
        return ResponseEntity.ok(User);
    }
    @PostMapping("/selectUserById") // 使用POST方法
    public ResponseEntity<User> selectUserById(@RequestBody User request) {
        User user = UserMapper.findById(request.getUserId()); // 使用传入的userId参数查找用户
        if (user == null) {
            return ResponseEntity.notFound().build(); // 如果没有找到对应的用户，返回 404 Not Found
        }
        return ResponseEntity.ok(user); // 返回找到的用户信息
    }
}