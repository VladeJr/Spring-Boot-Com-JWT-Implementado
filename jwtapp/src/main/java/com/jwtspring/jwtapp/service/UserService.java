package com.jwtspring.jwtapp.service;

import com.jwtspring.jwtapp.dto.RegisterRequest;
import com.jwtspring.jwtapp.entity.User;
import com.jwtspring.jwtapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(new BCryptPasswordEncoder().encode(request.getPassword()))
                .role(request.getRole())
                .build();

        return userRepository.save(user);
    }
}
