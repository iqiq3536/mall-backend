package com.example.mallxx.controller;


import com.example.mallxx.Service.MailService;
import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.mallxx.Service.RandomNumberService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserMapper UserMapper;

    @Autowired
    private MailService mailService;

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
    @RequestMapping("/findUserById") // 使用POST方法
    public ResponseEntity<User> findUserById(@CookieValue(value = "user_id", required = false)String User_id ) {
        //System.out.println(request.getUser_id()+"**********-------------------------");
        System.out.println(User_id);
        User user = UserMapper.findById(Integer.parseInt(User_id)); // 使用传入的userId参数查找用户
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
        System.out.println(user);
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
    /*
    @PostMapping("/findUserByUsername")
    public ResponseEntity<Map<String, Object>> findUserByUsername(@RequestBody User request) {
        User user = UserMapper.findByUsername(request.getUsername());
        if (user == null) {
            Map<String, Object> responseBody = new HashMap<>();
            responseBody.put("message", "User not found");
            return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }*/
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
    /*@PostMapping("/findUsersByFamilyId")
    public ResponseEntity<List<User>> findUsersByFamilyId(@RequestBody User request) {
        List<User> users = UserMapper.findUsersByFamilyId(request.getFamily_id());
        if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(users);
    }*/

    // 通过家庭id查用户，返回list<user>
    @PostMapping("/findUsersByFamilyId")
    public ResponseEntity<List<User>> findUsersByFamilyId(@RequestBody User request) {
        List<User> users = UserMapper.findUsersByFamilyId(request.getFamily_id());
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(users);
    }
    //通过id更新用户，返回boolean
    /*@PostMapping("/updateUserById")
    public boolean updateUserById(@RequestBody User request) {
        // 调用UserMapper的updateUser方法，并传递整个User对象
        return UserMapper.updateUser(request);  // 假设userMapper已经通过依赖注入获得
    }*/
    // 通过id更新用户，返回包装了布尔结果的ResponseEntity
    @PostMapping("/updateUserById")
    public ResponseEntity<Boolean> updateUserById(@RequestBody User request) {
        boolean isUpdated = UserMapper.updateUser(request);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            // 如果更新失败，返回400 Bad Request 或者其他适合的状态码
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
    //注册新用户
    // 处理验证码验证的请求
    @PostMapping("/verifyCaptcha")
    public ResponseEntity<Map<String, Object>> verifyCaptcha(@RequestParam String email, @RequestParam String captcha) {
        Map<String, Object> response = new HashMap<>();

        // 验证验证码
        boolean valid = mailService.validateCaptcha(email, captcha);

        if (valid) {
            response.put("success", true);
            response.put("message", "验证码验证成功");
        } else {
            response.put("success", false);
            response.put("message", "验证码无效或已过期");
        }

        return ResponseEntity.ok(response);
    }

    // 处理用户注册
    // 处理用户注册
    // 处理用户注册
    @PostMapping("/addUser")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User request, @RequestParam String captcha) {
        Map<String, Object> response = new HashMap<>();

        // 验证验证码，使用request.getContactInfo()获取邮箱
        boolean valid = mailService.validateCaptcha(request.getContact_info(), captcha);

        if (!valid) {
            response.put("success", false);
            response.put("message", "验证码无效或已过期");
            return ResponseEntity.ok(response);
        }

        // 检查用户名是否已经存在
        boolean userExists = UserMapper.checkUserExists(request.getUsername());

        if (userExists) {
            response.put("success", false);
            response.put("message", "用户名已存在");
        } else {
            // 如果用户名不存在，则进行插入操作
            boolean success = UserMapper.addUser(request);

            if (success) {
                response.put("success", true);
                response.put("message", "注册成功");
            } else {
                response.put("success", false);
                response.put("message", "注册失败");
            }
        }

        return ResponseEntity.ok(response);
    }



    // 处理验证码发送请求
    @PostMapping("/sendEmail")
    public ResponseEntity<Map<String, Object>> sendTestEmail(@RequestBody Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();
        String email = request.get("email");  // 从请求体中获取 email 参数

        // 生成验证码
        String code = mailService.generateCaptcha();
        mailService.saveCaptcha(email, code);

        // 发送验证码邮件
        try {
            mailService.sendTestEmail(email, "您的验证码是：" + code);
            response.put("success", true);
            response.put("message", "验证码已发送到邮箱");
        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", "验证码发送失败: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }



    // 根据ID删除用户信息
    @PostMapping("/deleteUser")
    public boolean deleteUser(@RequestBody User request) {
        return UserMapper.deleteUser(request.getUser_id());
    }



}