package com.example.mallxx.controller;

import com.example.mallxx.mapper.AdminMapper;
import com.example.mallxx.mapper.MerchantMapper;
import com.example.mallxx.mapper.SellerMapper;
import com.example.mallxx.mapper.UserMapper;
import com.example.mallxx.entity.Merchant;
import com.example.mallxx.entity.User;
import com.example.mallxx.entity.Admin;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api")
@RestController
public class LoginController {
    private final MerchantMapper merchantMapper;
    private final UserMapper userMapper;

    private final AdminMapper adminMapper;

    public LoginController(MerchantMapper merchantMapper, UserMapper userMapper, AdminMapper adminMapper) {
        this.userMapper = userMapper;
        this.merchantMapper = merchantMapper;
        this.adminMapper = adminMapper;
    }

    @RequestMapping("/login_user")
    public ResponseEntity<Map<String, Object>> login_user(@RequestBody Map<String, String> loginData, HttpServletResponse httpServletresponse) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println(username + " " + password);
        Map<String, Object> response = new HashMap<>();
        User user = userMapper.loginUser(username, password);
        if (user != null) {
            int user_id = user.getUser_id();
            Cookie userIdCookie = new Cookie("user_id", String.valueOf(user_id));
            userIdCookie.setHttpOnly(true);
            userIdCookie.setMaxAge(3600);
            userIdCookie.setPath("/");
            httpServletresponse.addCookie(userIdCookie);
            System.out.println("1");
            response.put("success", true);
            response.put("message", "登录成功！");
        } else {
            System.out.println("2");
            response.put("success", false);
            response.put("message", "登录失败：用户名或密码错误");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/login_merchant")
    public ResponseEntity<Map<String, Object>> login_merchant(@RequestBody Map<String, String> loginData, HttpServletResponse httpServletresponse) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println(username + " " + password);
        Map<String, Object> response = new HashMap<>();
        Merchant merchant = merchantMapper.findByUsernameAndPassword(username, password).get(0);
        if ( merchant != null) {
            Cookie userIdCookie = new Cookie("merchantId", String.valueOf(merchant.getId()));
            userIdCookie.setHttpOnly(true);
            userIdCookie.setMaxAge(3600);
            userIdCookie.setPath("/");
            httpServletresponse.addCookie(userIdCookie);
            System.out.println("1");
            response.put("success", true);
            response.put("message", "登录成功！");
        }
      else {
            System.out.println("2");
            response.put("success", false);
            response.put("message", "登录失败：用户名或密码错误");
        }
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/login_admin")
    public ResponseEntity<Map<String, Object>> login_admin(@RequestBody Map<String, String> loginData, HttpServletResponse httpServletresponse) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println(username + " " + password);
        Map<String, Object> response = new HashMap<>();
        Admin admin = adminMapper.loginAdmin(username, password);
        if ( admin != null) {
            Cookie userIdCookie = new Cookie("adminId", String.valueOf(admin.getAdminId()));
            userIdCookie.setHttpOnly(true);
            userIdCookie.setMaxAge(3600);
            userIdCookie.setPath("/");
            httpServletresponse.addCookie(userIdCookie);
            System.out.println("1");
            response.put("success", true);
            response.put("message", "登录成功！");
        } else {
            System.out.println("2");
            response.put("success", false);
            response.put("message", "登录失败：用户名或密码错误");
        }
        return ResponseEntity.ok(response);
    }
}
