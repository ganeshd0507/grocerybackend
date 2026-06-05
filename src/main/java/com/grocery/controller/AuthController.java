package com.grocery.controller;

import com.grocery.dto.AuthResponse;
import com.grocery.dto.LoginRequest;
import com.grocery.dto.RegisterRequest;
import com.grocery.entity.Role;
import com.grocery.entity.User;
import com.grocery.repository.UserRepository;
import com.grocery.security.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication Controller", description = "Endpoints for user registration and authentication")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Creates a new customer or admin account and returns user details with a JWT token.")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email address is already in use.");
        }

        Role role = request.getRole();
        if (role == null) {
            role = Role.USER;
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(role)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        AuthResponse.UserResponseDTO userDto = AuthResponse.UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .savedAddresses(new ArrayList<>())
                .wishlist(new ArrayList<>())
                .build();

        AuthResponse response = AuthResponse.builder()
                .user(userDto)
                .token(jwtToken)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Checks email and password against database records. Returns user details and a secure JWT token upon success.")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found after authentication"));

        String jwtToken = jwtService.generateToken(user);

        AuthResponse.UserResponseDTO userDto = AuthResponse.UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .savedAddresses(new ArrayList<>())
                .wishlist(new ArrayList<>())
                .build();

        AuthResponse response = AuthResponse.builder()
                .user(userDto)
                .token(jwtToken)
                .build();

        return ResponseEntity.ok(response);
    }
}
