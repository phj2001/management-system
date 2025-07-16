package com.example.management_system;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:5173")
public class HelloController {
    @GetMapping("/api/hello")
    public String hello() {
        return "你好，这是一个来自后端的返回消息！";
    }
}
