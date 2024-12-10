package com.coursework.ticketbookingsystem.ticketpool;

import com.coursework.ticketbookingsystem.configuration.Logger;
import com.coursework.ticketbookingsystem.configuration.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class TicketPool {
    private static List<Integer> tickets;
    private static int maxTicketCapacity;
    private static int totalTicketsSold;

    // Constructor
    public TicketPool() {
        this.maxTicketCapacity = Configuration.maxTicketCapacity;
        this.tickets = Collections.synchronizedList(new LinkedList<>());

        // Initialize with available tickets and also it does not exceed max capacity
        int initialTickets = Math.min(Configuration.currentTicketsAvailable, maxTicketCapacity);
        for (int i = 0; i < initialTickets; i++) {
            tickets.add(1);
        }
        totalTicketsSold = 0;

        Logger.logToConsole(toString());
    }

    @Override
    public String toString() {
        return "TicketPool {" + "Current Tickets Available=" + tickets.size() + ", Max Ticket Capacity=" + maxTicketCapacity + ", Total Tickets Sold=" + totalTicketsSold + '}';
    }

    // Adds tickets to the ticketPool
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

    // Removes tickets from the ticketPool
    public synchronized boolean removeTickets(int numberOfTickets) {
        int currentAvailable = tickets.size();

        if (currentAvailable >= numberOfTickets) {
            for (int i = 0; i < numberOfTickets; i++) {
                tickets.remove(0);
            }
            totalTicketsSold += numberOfTickets;
            Logger.logToConsole("Removed " + numberOfTickets + " tickets. Total tickets sold: " + totalTicketsSold + ". Current available tickets: " + tickets.size());
            return true;
        } else {
            Logger.logToConsole("Not enough tickets available to remove " + numberOfTickets + " tickets.");
            return false;
        }
    }


    //get the total tickets sold
    public static int getTotalTicketsSold() {
        return totalTicketsSold;
    }

    public static int getCurrentTicketsForSale() {
        return tickets.size();
    }


    // Calculates the remaining tickets that can still be sold
    public static int getRemainingTicketsForSale() {
        return maxTicketCapacity - (totalTicketsSold + tickets.size());
    }

    // Check if the tickets are sold out
    public boolean isSoldOut() {
        return tickets.size() == 0 && totalTicketsSold >= maxTicketCapacity;
    }
}
