package com.coursework.ticketbookingsystem.customer;

import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.ticketpool.TicketPool;

public class Customer implements Runnable {
    private final int customerId;
    private final int retrievalInterval; // Time in milliseconds between purchase attempts
    private final TicketPool ticketPool;
    private final int ticketsToPurchase;

    public Customer(int customerId, int ticketsToPurchase, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        this.ticketsToPurchase = ticketsToPurchase;
        this.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        try {
            while (true) {
                boolean purchased = ticketPool.removeTickets(ticketsToPurchase);
                Logger.logToConsole("Customer " + customerId + " attempted to purchase " + ticketsToPurchase + " tickets.");
                if (!purchased) {
                    Logger.logToConsole("Customer " + customerId + " could not purchase tickets due to insufficient availability.");
                }
                Thread.sleep(retrievalInterval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.logToConsole("Customer " + customerId + " was interrupted.");
        }
    }
}
