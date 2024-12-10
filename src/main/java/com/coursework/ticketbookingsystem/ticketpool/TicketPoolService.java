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

    // Get the number of tickets sold
    public int getTicketsSold() {
        return TicketPool.getTotalTicketsSold();
    }

    public void updateConfiguration(int maxCapacity, int ticketReleaseRate, int customerRetrievalRate, int totalTickets) {
        synchronized (Configuration.class) {
            Configuration.maxTicketCapacity = maxCapacity;
            Configuration.ticketReleaseRate = ticketReleaseRate;
            Configuration.customerRetrievalRate = customerRetrievalRate;

            // Adjust ticket availability
            int currentTickets = ticketPool.getCurrentTicketsForSale();
            int ticketDifference = totalTickets - currentTickets;

            Configuration.currentTicketsAvailable = totalTickets;

            ticketPool.addTickets(ticketDifference);
        }
    }

}
