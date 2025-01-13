package com.example.mallxx.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.mallxx.mapper.AdminMapper;
import com.example.mallxx.mapper.UserMapper;
import com.example.mallxx.entity.Admin;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminMapper adminMapper;

    public AdminController(AdminMapper adminMapper, UserMapper UserMapper) {
        this.adminMapper = adminMapper;
    }
    @GetMapping("/adminList")
    public ResponseEntity<List<Admin>> getAllUser() {
        //List<User> User = UserMapper.findAll();
        List<Admin> Admin = adminMapper.selectAll();

        return ResponseEntity.ok(Admin);
    }


}
