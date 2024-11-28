package com.coursework.ticketbookingsystem.signup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class signup {

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> userDetails) {
        String fullName = userDetails.get("fullName");
        String email = userDetails.get("email");
        String username = userDetails.get("username");
        String password = userDetails.get("password");

        // Example validation or storage logic
        if (username.isEmpty() || password.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Missing required fields"));
        }

        // Simulate success response
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Signup successful"));
    }
}

