package com.coursework.ticketbookingsystem.vendor;

import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.ticketpool.TicketPool;

public class Vendor implements Runnable {
    private int vendorID;
    private int ticketsPerRelease;
    private int releaseInterval;
    private TicketPool ticketPool;

    public Vendor(int ticketsPerRelease, TicketPool ticketPool) {
        this.ticketsPerRelease = ticketsPerRelease;
        this.ticketPool = ticketPool;
    }

    public Vendor(int vendorID, int ticketsPerRelease, int releaseInterval, TicketPool ticketPool) {
        this.vendorID = vendorID;
        this.ticketsPerRelease = ticketsPerRelease;
        this.releaseInterval = releaseInterval;
        this.ticketPool = ticketPool;
    }



    public int getVendorID(){
        return vendorID;
    }

    public int getTicketsPerRelease(){
        return ticketsPerRelease;
    }

    public int getReleaseInterval(){
        return releaseInterval;
    }

    public void setVendorID(int vendorID){
        this.vendorID = vendorID;
    }

    public void setTicketsPerRelease(int ticketsPerRelease){
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public void setReleaseInterval(int releaseInterval){
        this.releaseInterval = releaseInterval;
    }


    @Override
    public void run() {
        try {
            while (true) {
                // Simulate ticket release
                ticketPool.addTickets(ticketsPerRelease);
                Logger.logToConsole("Vendor " + vendorID + " released " + ticketsPerRelease + " tickets.");

                // Wait for the specified release interval before releasing again
                Thread.sleep(releaseInterval);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.logToConsole("Vendor " + vendorID + " was interrupted.");
        }
    }
}
