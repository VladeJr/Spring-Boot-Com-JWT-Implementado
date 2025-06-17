package com.jwtspring.jwtapp.controller;

import com.jwtspring.jwtapp.entity.User;
import com.jwtspring.jwtapp.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

    @PutMapping("/me")
    public ResponseEntity<User> updateMyProfile(@AuthenticationPrincipal User user,
                                                @RequestParam(required = false) String name,
                                                @RequestParam(required = false) @NotBlank String password) {
        if (name != null) user.setName(name);
        if (password != null && !password.isBlank()) {
            user.setPassword(password); // Você pode aplicar encode se quiser
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }
}
