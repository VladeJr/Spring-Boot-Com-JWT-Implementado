package com.jwtspring.jwtapp.controller;

import com.jwtspring.jwtapp.dto.RegisterRequest;
import com.jwtspring.jwtapp.entity.User;
import com.jwtspring.jwtapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterRequest request) {
        User createdUser = userService.registerUser(request);
        return ResponseEntity.ok(createdUser);
    }
}
