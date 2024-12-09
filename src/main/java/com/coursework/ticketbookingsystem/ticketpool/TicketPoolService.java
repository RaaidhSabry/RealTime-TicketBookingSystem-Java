package com.coursework.ticketbookingsystem.ticketpool;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {

    private final TicketPool ticketPool;  // Inject TicketPool into the service

    @Autowired
    public TicketPoolService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Logic for purchasing tickets
    public boolean buyTickets(String customerName, int ticketCount) {
        return ticketPool.removeTickets(ticketCount);  // Remove tickets from the shared pool
    }

    // Get the number of tickets sold
    public int getTicketsSold() {
        return TicketPool.getTotalTicketsSold();  // Get from TicketPool class
    }

    public void updateConfiguration(int maxCapacity, int ticketReleaseRate, int customerRetrievalRate, int totalTickets) {
        synchronized (Configuration.class) {
            // Update the configuration
            Configuration.maxTicketCapacity = maxCapacity;
            Configuration.ticketReleaseRate = ticketReleaseRate;
            Configuration.customerRetrievalRate = customerRetrievalRate;

            // Adjust ticket availability
            int currentTickets = ticketPool.getCurrentTicketsForSale();
            int ticketDifference = totalTickets - currentTickets;

            // Update current tickets available
            Configuration.currentTicketsAvailable = totalTickets;

            // Adjust the ticket pool for consistency
            ticketPool.addTickets(ticketDifference);
        }
    }

}
