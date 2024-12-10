package com.coursework.ticketbookingsystem.ticketpool;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "api/ticket")
public class TicketPoolController {

    @Autowired
    private TicketPoolService ticketPoolService;

    @Autowired
    private TicketPool ticketPool;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(TicketPoolController.class);

    @GetMapping("/info")
    public ResponseEntity<Map<String, Integer>> getTicketInfo() {
        int remainingTickets = ticketPool.getCurrentTicketsForSale();
        Map<String, Integer> response = new HashMap<>();
        response.put("remainingTickets", remainingTickets);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/data")
    public ResponseEntity<Map<String, Integer>> getCustomerTicketData() {
        Map<String, Integer> response = new HashMap<>();
        response.put("remainingTickets", ticketPool.getCurrentTicketsForSale());
        response.put("ticketsSold", ticketPoolService.getTicketsSold());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/customer/buy")
    public ResponseEntity<Map<String, String>> buyTickets(@RequestBody Map<String, Object> request) {
        Map<String, String> response = new HashMap<>();

        try {
            String customerName = (String) request.get("customerName");
            int ticketCount = (int) request.get("numberOfTickets");

            // Validate that the ticket count is greater than 0
            if (ticketCount <= 0) {
                logger.warn("Purchase attempt failed: Customer {} tried to buy {} tickets, but the count must be greater than 0.", customerName, ticketCount);
                response.put("message", "Ticket count must be greater than 0.");
                return ResponseEntity.badRequest().body(response);
            }

            logger.info("Purchase attempt: Customer {} is trying to buy {} tickets.", customerName, ticketCount);

            boolean isTicketPurchased = ticketPool.removeTickets(ticketCount);

            if (isTicketPurchased) {
                logger.info("Ticket purchase successful: Customer {} bought {} tickets.", customerName, ticketCount);
                response.put("message", "Ticket purchased successfully!");
                return ResponseEntity.ok(response);
            } else {
                logger.warn("Ticket purchase failed: Customer {} attempted to buy {} tickets, but not enough tickets are available.", customerName, ticketCount);
                response.put("message", "Failed to purchase ticket. Not enough tickets.");
                return ResponseEntity.status(500).body(response);
            }
        } catch (Exception e) {
            logger.error("Error during ticket purchase: {}", e.getMessage());
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

    // update configuration
    @PostMapping("/configuration")
    public ResponseEntity<Map<String, String>> updateConfiguration(@RequestBody Map<String, Integer> configData) {
        Map<String, String> response = new HashMap<>();

        try {
            logger.info("Received request to update configuration with data: {}", configData);

            int maxCapacity = configData.getOrDefault("maxTicketCapacity", Configuration.maxTicketCapacity);
            int totalTickets = configData.getOrDefault("totalTickets", Configuration.currentTicketsAvailable);
            int ticketReleaseRate = configData.getOrDefault("ticketReleaseRate", Configuration.ticketReleaseRate);
            int customerRetrievalRate = configData.getOrDefault("customerRetrievalRate", Configuration.customerRetrievalRate);

            logger.info("Extracted configuration values - Max Capacity: {}, Total Tickets: {}, Ticket Release Rate: {}, Customer Retrieval Rate: {}",
                    maxCapacity, totalTickets, ticketReleaseRate, customerRetrievalRate);

            // Validate the totalTickets
            if (totalTickets > maxCapacity) {
                logger.error("Validation error: Total tickets cannot exceed the maximum ticket capacity. Total: {} Max Capacity: {}", totalTickets, maxCapacity);
                throw new IllegalArgumentException("Total tickets cannot exceed the maximum ticket capacity.");
            }

            // Update configuration using the service
            ticketPoolService.updateConfiguration(maxCapacity, ticketReleaseRate, customerRetrievalRate, totalTickets);

            logger.info("Configuration updated successfully.");
            response.put("message", "Configuration updated successfully.");
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            logger.warn("Validation error while updating configuration: {}", e.getMessage());
            response.put("message", "Validation error: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            logger.error("An error occurred while updating configuration: {}", e.getMessage(), e);
            response.put("message", "An error occurred: " + e.getMessage());
            return ResponseEntity.status(500).body(response);
        }
    }

}
