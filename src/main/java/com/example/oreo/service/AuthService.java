package com.example.oreo.service;

import com.example.oreo.dto.RegisterRequest;
import com.example.oreo.entity.User;
import com.example.oreo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User register(RegisterRequest request) {

        // 🔥 VALIDACIONES CLAVE
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username ya existe");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email ya existe");
        }

        if (request.getRole().name().equals("BRANCH") && request.getBranch() == null) {
            throw new RuntimeException("Branch es obligatorio para usuarios BRANCH");
        }

        if (request.getRole().name().equals("CENTRAL")) {
            request.setBranch(null);
        }

        // 🔥 CREACIÓN
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword()) // luego lo encriptamos
                .role(request.getRole())
                .branch(request.getBranch())
                .createdAt(Instant.now())
                .build();

        return userRepository.save(user);
    }
}