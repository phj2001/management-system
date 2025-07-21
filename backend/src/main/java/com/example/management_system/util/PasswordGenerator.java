package com.example.management_system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String rawPassword = "password123";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("====================================================================");
        System.out.println("用于 'password123' 的BCrypt哈希值是: " + encodedPassword);
        System.out.println("请将此哈希值更新到数据库中 admin 用户的 password 字段！");
        System.out.println("====================================================================");
    }
}