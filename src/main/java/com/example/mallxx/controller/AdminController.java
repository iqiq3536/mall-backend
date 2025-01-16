package com.example.mallxx.controller;


import com.example.mallxx.entity.Merchant;
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
        return ResponseEntity.ok(Merchant);
    }
    /**
     * 根据id删除商家
     */
    @RequestMapping("/deleteMerchant")
    public boolean deleteMerchant(int id) {
        MerchantMapper.deleteById(id);
        return true;
    }

    /**
     * 根据id修改商家信息
     */
    @RequestMapping(value = "/updateMerchant", method = RequestMethod.PUT)
    public ResponseEntity<String> updateMerchant(@RequestBody Merchant merchant) {
        try {
            MerchantMapper.update(merchant.getId(), merchant.getUsername(),
                    merchant.getPassword(), merchant.getShop_name(),
                    merchant.getContact_info(), merchant.getAddress());
            return new ResponseEntity<>("Merchant updated successfully.", HttpStatus.OK);
        } catch (Exception e) {
            // Handle exception appropriately
            return new ResponseEntity<>("Failed to update merchant: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
