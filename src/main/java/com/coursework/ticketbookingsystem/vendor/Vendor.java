package com.coursework.ticketbookingsystem.vendor;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.ticketpool.TicketPool;

public class Vendor implements Runnable {
    private final int vendorId;
    public static int ticketsPerRelease = 50;
    public static int releaseInterval = 5000;
    private final TicketPool ticketPool;


    public Vendor(int vendorId, int ticketsPerRelease, int releaseInterval,TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }

    public Vendor(int vendorId, TicketPool ticketPool) {
        this.vendorId = vendorId;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        try {
            while (!ticketPool.isSoldOut()) {
                ticketPool.addTickets(ticketsPerRelease);
                Logger.logToConsole("Vendor " + vendorId + " added " + ticketsPerRelease + " tickets. Tickets available: " + ticketPool.getCurrentTicketsAvailable());
                Thread.sleep(releaseInterval); // Slow down by increasing multiplier (e.g., 120000 ms for 2 minutes).
            }
            Logger.logToConsole("Vendor " + vendorId + " stops adding tickets. Maximum capacity reached.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.logToConsole("Vendor " + vendorId + " was interrupted.");
        }
    }
}
