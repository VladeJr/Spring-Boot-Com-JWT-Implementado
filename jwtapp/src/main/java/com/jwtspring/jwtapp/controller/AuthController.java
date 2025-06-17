package com.jwtspring.jwtapp.controller;

import com.jwtspring.jwtapp.dto.JwtResponse;
import com.jwtspring.jwtapp.dto.UserLoginRequest;
import com.jwtspring.jwtapp.dto.UserRegisterRequest;
import com.jwtspring.jwtapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> register(@RequestBody UserRegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }
}
