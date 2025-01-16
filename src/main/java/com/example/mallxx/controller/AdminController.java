package com.example.mallxx.controller;


import com.example.mallxx.entity.Merchant;
import com.example.mallxx.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mallxx.mapper.AdminMapper;
import com.example.mallxx.mapper.UserMapper;
import com.example.mallxx.mapper.MerchantMapper;
import com.example.mallxx.entity.Admin;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminMapper adminMapper;
    private final MerchantMapper MerchantMapper;

    public AdminController(AdminMapper adminMapper,  UserMapper UserMapper, MerchantMapper MerchantMapper) {
        this.adminMapper = adminMapper;
        this.MerchantMapper = MerchantMapper;
    }
    @GetMapping("/adminList")
    public ResponseEntity<List<Admin>> getAllUser() {
        //List<User> User = UserMapper.findAll();
        List<Admin> Admin = adminMapper.selectAll();
        System.out.println(Admin);

        return ResponseEntity.ok(Admin);
    }
    /**
     * 获取所有商家Merchant的信息
     * 返回列表
     */
    @GetMapping("/merchantList")
    public ResponseEntity<List<Merchant>> getAllMerchant() {
        //List<User> User = UserMapper.findAll();
        List<Merchant> Merchant = MerchantMapper.findAll();
        System.out.println(Merchant);
        return ResponseEntity.ok(Merchant);
    }

    /**
     * 根据id删除商家
     */
    @PostMapping("/deleteMerchant")
    public boolean deleteMerchant(@RequestBody User request) {
        return MerchantMapper.deletemerchant(request.getUser_id());;
    }


    /**
     * 根据id修改商家信息
     */
    @PostMapping("/updateMerchant")
    public ResponseEntity<Boolean> updateMerchant(@RequestBody Merchant request) {
        boolean isUpdated = MerchantMapper.updateMerchant(request);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            // 如果更新失败，返回400 Bad Request 或者其他适合的状态码
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }



}
