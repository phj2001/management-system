package com.example.management_system;
import com.example.management_system.LoginRequest;
import com.example.management_system.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody LoginRequest loginRequest) {
        if ("admin".equals(loginRequest.getUsername())&&"password123".equals(loginRequest.getPassword())) {
            User user = new User(
                    "admin",
                    "管理员",
                    "https://i.pravatar.cc/150?u=admin"
            );
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("用户名或密码错误");
        }
    }
    @GetMapping("/hello")
    public String sayHello() {

        return "你好，这里是Spring Boot后端！";
    }
}
