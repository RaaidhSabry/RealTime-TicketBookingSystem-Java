package com.coursework.ticketbookingsystem.customer;

import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.ticketpool.TicketPool;

public class Customer implements Runnable {
    private final int customerId;
    public static int ticketsToPurchase = 1;
    public static int retrievalInterval = 1000;
    private final TicketPool ticketPool;

    public Customer(int customerId, int ticketsToPurchase, int retrievalInterval, TicketPool ticketPool) {
        this.customerId = customerId;
        Customer.ticketsToPurchase = ticketsToPurchase;
        Customer.retrievalInterval = retrievalInterval;
        this.ticketPool = ticketPool;
    }

    public Customer(int customerId, TicketPool ticketPool) {
        this.customerId = customerId;
        this.ticketPool = ticketPool;
    }

    // run thread for customer
    @Override
    public void run() {
        try {
            while (!ticketPool.isSoldOut()) {
                boolean purchased = ticketPool.removeTickets(ticketsToPurchase);
                Logger.logToConsole(toString() + (purchased ? " purchased tickets." : " could not purchase tickets."));
                Thread.sleep(retrievalInterval);
            }
            Logger.logToConsole(toString() + " stops trying. Tickets are sold out.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.logToConsole(toString() + " was interrupted.");
        }
    }

    @Override
    public String toString() {
        return "Customer {" + "Customer ID=" + customerId + ", Tickets to Purchase=" + ticketsToPurchase + '}';
    }
}
