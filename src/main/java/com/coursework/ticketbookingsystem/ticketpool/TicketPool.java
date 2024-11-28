package com.coursework.ticketbookingsystem.ticketpool;

import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.configuration.Configuration;
import com.coursework.ticketbookingsystem.vendor.Vendor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class TicketPool {
    private final List<Integer> tickets;
    private final int maxTicketCapacity;
    private int totalTicketsSold;

    // Constructor
    public TicketPool() {
        this.maxTicketCapacity = Configuration.maxTicketCapacity;
        this.tickets = Collections.synchronizedList(new LinkedList<>());

        // Initialize with available tickets, ensuring it does not exceed max capacity
        int initialTickets = Math.min(Configuration.currentTicketsAvailable, maxTicketCapacity);
        for (int i = 0; i < initialTickets; i++) {
            tickets.add(1);
        }
        totalTicketsSold = 0;

        Logger.logToConsole("TicketPool initialized with " + tickets.size() + " tickets.");
    }

    // Adds tickets to the pool, respecting max capacity
    public synchronized void addTickets(int numberOfTickets) {
        int ticketsRemainingForSale = getRemainingTicketsForSale();
        int ticketsToAdd = Math.min(numberOfTickets, ticketsRemainingForSale);

        if (ticketsToAdd > 0) {
            for (int i = 0; i < ticketsToAdd; i++) {
                tickets.add(1); // Add a ticket
            }
            Logger.logToConsole("Added " + ticketsToAdd + " tickets. Current available tickets: " + tickets.size());
        } else {
            Logger.logToConsole("Cannot add tickets. Maximum capacity of " + maxTicketCapacity + " has been reached.");
        }
    }

    // Removes tickets from the pool (tickets bought by a customer)
    public synchronized boolean removeTickets(int numberOfTickets) {
        int currentAvailable = tickets.size();

        if (currentAvailable >= numberOfTickets) {
            for (int i = 0; i < numberOfTickets; i++) {
                tickets.remove(0); // Remove a ticket
            }
            totalTicketsSold += numberOfTickets; // Increment the total tickets sold
            Logger.logToConsole("Removed " + numberOfTickets + " tickets. Total tickets sold: " + totalTicketsSold + ". Current available tickets: " + tickets.size());
            return true;
        } else {
            Logger.logToConsole("Not enough tickets available to remove " + numberOfTickets + " tickets.");
            return false;
        }
    }

    // Method to get the current number of tickets available for sale
    public synchronized int getCurrentTicketsAvailable() {
        return tickets.size();
    }

    // Method to get max ticket capacity
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    // Method to get the total tickets sold
    public int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    // Calculates the remaining tickets that can still be sold
    public int getRemainingTicketsForSale() {
        return maxTicketCapacity - (totalTicketsSold + tickets.size());
    }

    // Check if the tickets are sold out
    public boolean isSoldOut() {
        return tickets.size() == 0 && totalTicketsSold >= maxTicketCapacity;
    }
}
