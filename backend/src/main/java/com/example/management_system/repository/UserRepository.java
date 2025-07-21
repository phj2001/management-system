package com.example.management_system.repository;

import com.example.management_system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // 声明这是一个 Repository Bean
public interface UserRepository extends JpaRepository<User, Long> {

    // Spring Data JPA 的神奇之处：
    // 你只需按照规范定义一个方法名，它就会自动为你生成SQL查询！
    // "findByUsername" 会被自动翻译成 "SELECT * FROM users WHERE username = ?"
    Optional<User> findByUsername(String username);
}