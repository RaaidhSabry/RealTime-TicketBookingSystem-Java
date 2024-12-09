package com.coursework.ticketbookingsystem;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SystemController {

    private boolean areLogsRunning = false;

    @PostMapping("/api/system/start")
    public synchronized ResponseEntity<Map<String, String>> startSystemFromFrontend() {
        Map<String, String> response = new HashMap<>();
        try {
            if (areLogsRunning) {
                response.put("message", "System is already running.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            System.out.println("Starting Spring Boot Application...");
            TicketBookingSystemApplication.startSpringApplication(); // Trigger start logic
            areLogsRunning = true;

            response.put("message", "Spring Boot Application started successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Failed to start the Spring Boot Application.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/api/system/stop")
    public synchronized ResponseEntity<Map<String, String>> stopLogs() {
        Map<String, String> response = new HashMap<>();
        try {
            if (!areLogsRunning) {
                response.put("message", "Logs are not running.");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
            }

            // Stop logs and simulate returning to the main menu
            System.out.println("Stopping logs and returning to the main menu...");
            areLogsRunning = false;

            response.put("message", "Logs stopped, returned to main menu.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Failed to stop logs.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
