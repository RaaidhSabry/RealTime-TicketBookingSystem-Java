package com.coursework.ticketbookingsystem.vendor;

import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.ticketpool.TicketPool;

public class Vendor implements Runnable {
    private final int vendorId;
    public static int ticketsPerRelease = 50;
    public static int releaseInterval = 5000;
    private final TicketPool ticketPool;

    public Vendor(int vendorId, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.vendorId = vendorId;
        Vendor.ticketsPerRelease = ticketsPerRelease;
        Vendor.releaseInterval = releaseInterval;
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
                Logger.logToConsole(toString() + " added tickets.");
                Thread.sleep(releaseInterval);
            }
            Logger.logToConsole(toString() + " stops adding tickets.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.logToConsole(toString() + " was interrupted.");
        }
    }

    @Override
    public String toString() {
        return "Vendor {" +
                "Vendor ID=" + vendorId +
                ", Tickets Per Release=" + ticketsPerRelease +
                '}';
    }
}
