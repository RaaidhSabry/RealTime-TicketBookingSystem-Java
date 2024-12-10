package com.coursework.ticketbookingsystem.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Value("${user.file.path}")
    private String userFilePath;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        try {
            if (userService.findByEmail(user.getEmail()) != null) {
                logger.warn("Signup failed: Email {} is already registered.", user.getEmail());
                return ResponseEntity.status(400).body("Email already registered");
            }
            userService.saveUser(user);
            logger.info("Signup successful for email: {}", user.getEmail());
            return ResponseEntity.status(200).body("Signup successful");
        } catch (IOException e) {
            logger.error("Signup error: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error occurred during signup");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            User existingUser = userService.findByEmail(user.getEmail());
            if (existingUser == null) {
                logger.warn("Login failed: Email {} not found.", user.getEmail());
                return ResponseEntity.status(400).body("Invalid email or password");
            }

            // Check if the password and role match
            if (!existingUser.getPassword().equals(user.getPassword()) || !existingUser.getRole().equals(user.getRole())) {
                logger.warn("Login failed: Incorrect credentials for email {}.", user.getEmail());
                return ResponseEntity.status(400).body("Invalid email, password, or role");
            }

            // Successful login
            logger.info("Login successful for email: {}, role: {}", user.getEmail(), user.getRole());
            return ResponseEntity.status(200).body("Login successful");
        } catch (Exception e) {
            logger.error("Login error: {}", e.getMessage());
            return ResponseEntity.status(500).body("Error occurred during login");
        }
    }


}
