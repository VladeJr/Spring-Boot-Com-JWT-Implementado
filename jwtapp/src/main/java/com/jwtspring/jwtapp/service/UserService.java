package com.jwtspring.jwtapp.service;

import com.jwtspring.jwtapp.dto.JwtResponse;
import com.jwtspring.jwtapp.dto.UserLoginRequest;
import com.jwtspring.jwtapp.dto.UserRegisterRequest;
import com.jwtspring.jwtapp.entity.User;
import com.jwtspring.jwtapp.enums.Role;
import com.jwtspring.jwtapp.repository.UserRepository;
import com.jwtspring.jwtapp.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public JwtResponse register(UserRegisterRequest request) {
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
        String token = jwtUtil.generateToken(user);
        return new JwtResponse(token);
    }

    public JwtResponse login(UserLoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtUtil.generateToken(user);
        return new JwtResponse(token);
    }
}
