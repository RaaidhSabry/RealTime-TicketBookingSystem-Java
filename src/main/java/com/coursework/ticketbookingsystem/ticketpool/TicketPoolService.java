package com.coursework.ticketbookingsystem.ticketpool;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import com.coursework.ticketbookingsystem.vendor.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {

    private TicketPool ticketPool;

    @Autowired
    public TicketPoolService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Service method to add tickets, considering max capacity
    public String addTickets(int numberOfTickets) {
        int remainingCapacity = ticketPool.getRemainingTicketsForSale();

        if (remainingCapacity > 0) {
            // Add only the tickets that can fit without exceeding capacity
            int ticketsToAdd = Math.min(numberOfTickets, remainingCapacity);
            ticketPool.addTickets(ticketsToAdd);

            return "Added " + ticketsToAdd + " tickets successfully. Current available tickets: " + ticketPool.getCurrentTicketsAvailable();
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
