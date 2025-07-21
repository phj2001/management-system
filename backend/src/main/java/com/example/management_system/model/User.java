package com.example.management_system.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity // 告诉JPA，这是一个实体类，它映射到数据库的一张表
@Table(name = "users") // 明确指定映射到数据库中的 "users" 表
@Data // Lombok 注解，自动生成 getter, setter 等
public class User {

    @Id // 标记这是主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键生成策略：自增
    private Long id;

    @Column(nullable = false, unique = true) // 字段约束：不能为空，且唯一
    private String username;

    @Column(nullable = false)
    private String password; // 同样，这里应该是加密后的密码

    @Column(nullable = false)
    private String name;

    private String avatarUrl;
}