package com.coursework.ticketbookingsystem.ticketpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/ticketpool")
public class TicketPoolController {

    private final TicketPoolService ticketPoolService;

    @Autowired
    public TicketPoolController(TicketPoolService ticketPoolService){
        this.ticketPoolService = ticketPoolService;
    }

    // Endpoint to add tickets
    @PostMapping("/add")
    public String addTickets(@RequestParam int numberOfTickets) {
        return ticketPoolService.addTickets(numberOfTickets);
    }

    // Endpoint to remove tickets
    @PostMapping("/remove")
    public String removeTickets(@RequestParam int numberOfTickets) {
        return ticketPoolService.removeTickets(numberOfTickets);
    }

    // Endpoint to get current total tickets
    @GetMapping("/total")
    public int getCurrentTotalTickets() {
        return ticketPoolService.getCurrentTotalTickets();
    }

    // Endpoint to get max ticket capacity
    @GetMapping("/capacity")
    public int getMaxTicketCapacity() {
        return ticketPoolService.getMaxTicketCapacity();
    }

    @GetMapping("/currentTickets")
    public int getCurrentTickets() {
        return 100; // Example response
    }
}
