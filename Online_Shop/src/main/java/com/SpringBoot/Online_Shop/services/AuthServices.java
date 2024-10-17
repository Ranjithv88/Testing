package com.SpringBoot.Online_Shop.services;

import com.SpringBoot.Online_Shop.model.ERole;
import com.SpringBoot.Online_Shop.model.User;
import com.SpringBoot.Online_Shop.repository.UserRepository;
import com.SpringBoot.Online_Shop.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServices {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public ResponseEntity<String> register(User user) {
        var users = User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(ERole.USER).build();
        repository.save(users);
        return new ResponseEntity<>("Register Successfully .....!", HttpStatus.CREATED);
    }

    public ResponseEntity<?> authenticate(User login) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        Optional<User> userDetails = repository.findByEmail(login.getEmail());
        if (userDetails.isPresent()) {
            String token = jwtUtils.generateToken(userDetails.get());
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

}

