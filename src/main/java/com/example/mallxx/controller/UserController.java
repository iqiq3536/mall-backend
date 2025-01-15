package com.example.mallxx.controller;


import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.UserMapper;
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
        // 假设userMapper已经通过依赖注入获得
        boolean isUpdated = UserMapper.updateUser(request);
        if (isUpdated) {
            return ResponseEntity.ok(true);
        } else {
            // 如果更新失败，返回400 Bad Request 或者其他适合的状态码
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
    //添加用户，返回boolean
    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User request) {
        //request.setUser_id(UserMapper.findAll().size()+1);
        request.setUser_id(RandomNumberService.generateRandomInt());
        return UserMapper.addUser(request);
    }
    // 根据ID删除用户信息
    @PostMapping("/deleteUser")
    public boolean deleteUser(@RequestBody User request) {
        return UserMapper.deleteUser(request.getUser_id());
    }



}