package com.coursework.ticketbookingsystem.ticketpool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {

    private final TicketPool ticketPool;

    @Autowired
    public TicketPoolService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Service method to add tickets, considering max capacity
    public String addTickets(int numberOfTickets) {
        if (ticketPool.getRemainingTicketsForSale() > 0 && ticketPool.getCurrentTicketsAvailable() + numberOfTickets <= ticketPool.getRemainingTicketsForSale()) {
            ticketPool.addTickets(numberOfTickets);
            return "Tickets added successfully. Current available tickets: " + ticketPool.getCurrentTicketsAvailable();
        } else {
            return "Cannot add tickets, maximum ticket capacity of " + ticketPool.getMaxTicketCapacity() + " has been reached or would be exceeded.";
        }
    }

    // Service method to remove tickets (customer buying tickets)
    public String removeTickets(int numberOfTickets) {
        if (!ticketPool.isSoldOut() && ticketPool.removeTickets(numberOfTickets)) {
            return "Tickets removed successfully. Current available tickets: " + ticketPool.getCurrentTicketsAvailable();
        } else {
            return "Cannot remove tickets. Either sold out or not enough tickets available.";
        }
    }

    // Service method to get current available tickets
    public int getCurrentTotalTickets() {
        return ticketPool.getCurrentTicketsAvailable();
    }

    // Service method to get total tickets sold
    public int getTotalTicketsSold() {
        return ticketPool.getTotalTicketsSold();
    }

    // Service method to get the max ticket capacity
    public int getMaxTicketCapacity() {
        return ticketPool.getMaxTicketCapacity();
    }
}
