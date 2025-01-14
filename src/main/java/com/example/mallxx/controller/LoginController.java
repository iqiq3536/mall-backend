package com.example.mallxx.controller;

import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.SellerMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.UserMapper;
@RequestMapping("/api")
@RestController
public class LoginController {
    private final SellerMapper sellerMapper;
    private final UserMapper UserMapper;

    public LoginController(SellerMapper sellerMapper, com.example.mallxx.mapper.UserMapper userMapper) {
        this.sellerMapper = sellerMapper;
        UserMapper = userMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println(username + " " + password);
        Map<String, Object> response = new HashMap<>();
        if (!sellerMapper.findByUsernameAndPassword(username, password).isEmpty()) {
            System.out.println("1");
            User user = UserMapper.findByUsername(username);
            response.put("success", true);
            response.put("message", "登录成功！");
            response.put("user", user);
            //token
        } else {
            System.out.println("2");
            response.put("success", false);
            response.put("message", "登录失败：用户名或密码错误");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login1")
    public ResponseEntity<Map<String, Object>> login1(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println(username + " " + password);
        Map<String, Object> response = new HashMap<>();
        if (!sellerMapper.findByUsernameAndPassword(username, password).isEmpty()) {
            System.out.println("1");
            User user = UserMapper.findByUsername(username);
            response.put("success", true);
            response.put("message", "登录成功！");
            response.put("user", user);
            //token
        } else {
            System.out.println("2");
            response.put("success", false);
            response.put("message", "登录失败：用户名或密码错误");
        }
        return ResponseEntity.ok(response);
    }
}
