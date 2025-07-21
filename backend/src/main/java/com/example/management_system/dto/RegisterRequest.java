package com.example.management_system.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    // 在真实项目中，这里应该加上数据校验注解，如 @NotBlank, @Size 等
    // 需要添加 spring-boot-starter-validation 依赖
    private String username;
    private String password;
    private String name;
}