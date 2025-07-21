package com.example.management_system.controller;

import com.example.management_system.dto.LoginRequest;
import com.example.management_system.dto.RegisterRequest; // 确保这个 import 存在
import com.example.management_system.dto.UserResponse;
import com.example.management_system.model.User;
import com.example.management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired; // 确保这个 import 存在
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    // 关键点：声明 AuthService 字段，并使用 @Autowired 进行注入
    // @Autowired 告诉 Spring Boot：“请自动找到一个 AuthService 类型的 Bean，并把它赋值给我”
    @Autowired
    private AuthService authService;

    // 登录方法 (保持不变)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = authService.login(loginRequest);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserResponse userResponse = new UserResponse(user.getUsername(), user.getName(), user.getAvatarUrl());
            return ResponseEntity.ok(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }

    // 新增的注册端点
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // 现在 authService 已经被正确注入，这里可以正常调用
            User registeredUser = authService.register(registerRequest);
            UserResponse userResponse = new UserResponse(
                    registeredUser.getUsername(),
                    registeredUser.getName(),
                    registeredUser.getAvatarUrl()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}