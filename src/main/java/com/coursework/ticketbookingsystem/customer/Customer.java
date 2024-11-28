package com.coursework.ticketbookingsystem.customer;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.ticketpool.TicketPool;

public class Customer implements Runnable {
    private final int customerId;
    public static int ticketsToPurchase=1;
    public static int retrievalInterval=1000;
    private final TicketPool ticketPool;


    public Customer(int customerId, int ticketsToPurchase, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.ticketsToPurchase = ticketsToPurchase;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    public Customer(int customerId, TicketPool ticketPool){
        this.customerId = customerId;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        try {
            while (!ticketPool.isSoldOut()) {
                boolean purchased = ticketPool.removeTickets(ticketsToPurchase);
                if (purchased) {
                    Logger.logToConsole("Customer " + customerId + " purchased " + ticketsToPurchase + " tickets.");
                } else {
                    Logger.logToConsole("Customer " + customerId + " could not purchase tickets. Tickets available: " + ticketPool.getCurrentTicketsAvailable());
                }
                Thread.sleep(retrievalInterval); // Slow down by increasing multiplier (e.g., 3000 ms).
            }
            Logger.logToConsole("Customer " + customerId + " stops trying. Tickets are sold out.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.logToConsole("Customer " + customerId + " was interrupted.");
        }
    }
}
