package com.example.management_system.service;

import com.example.management_system.dto.LoginRequest;
import com.example.management_system.model.User;
import com.example.management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.management_system.dto.RegisterRequest; // 新增
import org.springframework.security.crypto.password.PasswordEncoder; // 新增
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // 注入密码编码器

    // --- 修改登录逻辑以使用密码编码器 ---
    public Optional<User> login(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByUsername(loginRequest.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // 使用 passwordEncoder.matches 来比对明文密码和数据库中的哈希密码
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // --- 新增注册逻辑 ---
    public User register(RegisterRequest registerRequest) throws Exception {
        // 1. 检查用户名是否已存在
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new Exception("用户名 " + registerRequest.getUsername() + " 已被注册");
        }

        // 2. 创建新用户实例
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setName(registerRequest.getName());

        // 3. 对密码进行加密
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // 4. 设置一个默认头像
        newUser.setAvatarUrl("https://i.pravatar.cc/150?u=" + registerRequest.getUsername());

        // 5. 保存到数据库
        return userRepository.save(newUser);
    }
}