package com.example.mallxx.controller;

import com.example.mallxx.entity.User;
import com.example.mallxx.mapper.SellerMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
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

    /**
     *
     */
    /*@RequestMapping("/123")
    public ResponseEntity<Void> l123(@CookieValue(value = "user_id", required = false) String userid) {
        System.out.println("userid" + userid);
        return ResponseEntity.ok().build();
    }*/
    /**
     *
     * @param loginData
     * @return
     */

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
    @PostMapping("/login2")
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
    @RequestMapping("/login3")
    public ResponseEntity<Map<String, Object>> login3(@RequestBody Map<String, String> loginData, HttpServletResponse httpServletresponse) {
        String username = loginData.get("username");
        String password = loginData.get("password");

        Map<String, Object> response = new HashMap<>();

        User user = UserMapper.loginUser(username,password);
        if (user !=null) {
            System.out.println("FF00" + username + " " + password);
            System.out.println("1");
            Cookie userIdCookie = new Cookie("user_id",  String.valueOf(user.getUser_id()));
            userIdCookie.setHttpOnly(true);
            userIdCookie.setMaxAge(3600);
            userIdCookie.setPath("/");
            httpServletresponse.addCookie(userIdCookie);
            response.put("success", true);
            response.put("message", "登录成功！");
        } else {
            System.out.println("2");
            response.put("success", false);
            response.put("message", "登录失败：用户名或密码错误");
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login4")
    public ResponseEntity<Map<String, Object>> login4(@RequestBody Map<String, String> loginData) {
        String username = loginData.get("username");
        String password = loginData.get("password");
        System.out.println(username + " " + password);
        Map<String, Object> response = new HashMap<>();

        User user = UserMapper.loginUser(username,password);
        if (user != null) {
            System.out.println(username + " " + password);
            // 创建一个带有用户ID的cookie，不进行签名或加密
            System.out.println(user.getUser_id());
            Cookie userIdCookie = new Cookie("user_id", String.valueOf(user.getUser_id()));

            // 设置cookie，并添加必要的安全措施
            userIdCookie.setPath("/"); // 该路径下的所有请求都可访问此cookie
            userIdCookie.setHttpOnly(true); // 防止JavaScript访问该cookie以增加安全性
            userIdCookie.setSecure(true);   // 如果是HTTPS，则设置为true，确保通过HTTPS传输
            // 可选：设置过期时间，例如一天后过期
            // userIdCookie.setMaxAge(60 * 60 * 24);

            response.put("success", true);
            response.put("message", "登录成功！");
            response.put("user", user);

            // 创建带有附加Set-Cookie头的ResponseEntity
            MultiValueMap<String, String> headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, toHeader(userIdCookie));
            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            System.out.println("2");
            response.put("success", false);
            response.put("message", "登录失败：用户名或密码错误");
            return ResponseEntity.ok(response);
        }
    }

    // 辅助函数用于将Cookie对象转换为字符串表示形式（适合HTTP头）
    private String toHeader(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.getName()).append("=").append(cookie.getValue());
        if (cookie.getPath() != null) {
            sb.append("; Path=").append(cookie.getPath());
        }
        return sb.toString();
    }

}
