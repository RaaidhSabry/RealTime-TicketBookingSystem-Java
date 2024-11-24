package com.coursework.ticketbookingsystem.ticketpool;

import com.coursework.ticketbookingsystem.configuration.Logger;
import org.springframework.beans.factory.annotation.Value;
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
    public TicketPool(@Value("${ticketpool.maxTicketCapacity}") int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        this.tickets = Collections.synchronizedList(new LinkedList<>());
        this.totalTicketsSold = 0;
    }

    // Adds tickets to the pool, but only if max capacity is not exceeded
    public synchronized void addTickets(int numberOfTickets) {
        int ticketsRemainingForSale = maxTicketCapacity - totalTicketsSold;

        if (tickets.size() + numberOfTickets <= ticketsRemainingForSale) {
            for (int i = 0; i < numberOfTickets; i++) {
                tickets.add(1); // Add a ticket
            }
            Logger.logToConsole("Added " + numberOfTickets + " tickets. Current available tickets: " + tickets.size());
        } else {
            Logger.logToConsole("Cannot add tickets, maximum capacity reached or would be exceeded.");
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
            Logger.logToConsole("Not enough tickets available to remove " + numberOfTickets);
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
        return maxTicketCapacity - totalTicketsSold;
    }

    // Check if the tickets are sold out
    public boolean isSoldOut() {
        return totalTicketsSold >= maxTicketCapacity;
    }
}
