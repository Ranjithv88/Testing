package com.SpringBoot.Online_Shop.controllers;

import com.SpringBoot.Online_Shop.model.User;
import com.SpringBoot.Online_Shop.services.AuthServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServices service;

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody User login) {
        return service.authenticate(login);
    }

    @GetMapping("/user/getByEmail")
    public String getByEmail () {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}

