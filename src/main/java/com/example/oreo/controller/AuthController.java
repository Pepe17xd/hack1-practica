package com.example.oreo.controller;

import com.example.oreo.dto.RegisterRequest;
import com.example.oreo.entity.User;
import com.example.oreo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
        User user = authService.register(request);
        return ResponseEntity.status(201).body(user);
    }
}