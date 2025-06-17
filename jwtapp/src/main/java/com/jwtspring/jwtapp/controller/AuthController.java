package com.jwtspring.jwtapp.controller;

import com.jwtspring.jwtapp.dto.JwtResponse;
import com.jwtspring.jwtapp.dto.LoginRequest;
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

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest request) {
        String token = userService.authenticate(request);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
