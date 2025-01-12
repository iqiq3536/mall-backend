package com.example.mallxx.controller;


import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.UserMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
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
    //通过id查找用户，返回单个用户
    @PostMapping("/findUserById") // 使用POST方法
    public ResponseEntity<User> selectUserById(@RequestBody User request) {
        User user = UserMapper.findById(request.getUser_id()); // 使用传入的userId参数查找用户
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    //通过用户名username查找用户，返回单个用户
    @PostMapping("/findUserByUsername")
    public ResponseEntity<User> findUserByUsername(@RequestBody User request) {
        User user = UserMapper.findByUsername(request.getUsername());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
    //通过家庭名称查用户，返回list<user>
    @PostMapping("/findUsersByFamilyName")
    public ResponseEntity<List<User>> findUsersByFamilyName(@RequestBody User request) {
        List<User> users = UserMapper.findUsersByFamilyName(request.getFamily_name());
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }
    //通过家庭id查用户，返回list<user>
    @PostMapping("/findUsersByFamilyId")
    public ResponseEntity<List<User>> findUsersByFamilyId(@RequestBody User request) {
        List<User> users = UserMapper.findUsersByFamilyId(request.getFamily_id());
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }

}