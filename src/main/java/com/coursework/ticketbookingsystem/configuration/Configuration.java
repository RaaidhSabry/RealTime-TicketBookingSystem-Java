package com.coursework.ticketbookingsystem.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Configuration {
    private int totalTickets;
    private int ticketReleaseRate; // Rate in minutes
    private int customerRetrievalRate; // Rate in seconds
    private int maxTicketCapacity;
    private boolean isRunning = false; // Flag to control the ticket operations
    private int currentTicketsAvailable; // Track current available tickets
    private int ticketsSold = 0; // Track total tickets sold

    public Configuration() {}

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        if (totalTickets > 0 && totalTickets <= maxTicketCapacity) {
            this.totalTickets = totalTickets;
            this.currentTicketsAvailable = totalTickets;
        } else {
            throw new IllegalArgumentException("The number of tickets should be between 1 and " + maxTicketCapacity);
        }
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        if (ticketReleaseRate > 0 && ticketReleaseRate <= 5) {
            this.ticketReleaseRate = ticketReleaseRate;
        } else {
            throw new IllegalArgumentException("The ticket release rate should be between 1 and 5 minutes.");
        }
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        if(customerRetrievalRate > 0 && customerRetrievalRate <= 10){
            this.customerRetrievalRate = customerRetrievalRate;
        }else {
            throw new IllegalArgumentException("The customer retrieval rate should be between 1 and 10 minutes.");
        }
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        if (maxTicketCapacity > 0 && maxTicketCapacity <= 5000) {
            this.maxTicketCapacity = maxTicketCapacity;
        }
        else {
            throw new IllegalArgumentException("The max ticket capacity should be between 1 and 5000 tickets.");
        }
    }

    public int getCurrentTicketsAvailable(){
        return currentTicketsAvailable;
    }

    public int getTicketsSold(){
        return ticketsSold;
    }

    public void start() {
        System.out.println("Running the Command Line Interface (CLI)...");

        Scanner input = new Scanner(System.in);

        while (true){
            try{
                System.out.println("Enter the Maximum Capacity for the event : ");
                int maxTicketCapacity = input.nextInt();
                setMaxTicketCapacity(maxTicketCapacity);
                break;
            }catch (InputMismatchException e){
                System.out.println("Invalid input, please enter a number. ");
                input.next();
            }catch (IllegalArgumentException e){
                System.out.println("Invalid input, please enter a number between 1 and 5000");
            }
        }


        while (true) {
            try {
                System.out.print("Enter the total number of tickets: ");
                int totalTickets = input.nextInt();
                setTotalTickets(totalTickets);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input, please enter a number within the Maximum Ticket Capacity! .... Maximum Ticket Capacity = "+ maxTicketCapacity);
            }
        }

        while (true) {
            try {
                System.out.print("Enter the ticket release rate (in minutes): ");
                int releaseRate = input.nextInt();
                setTicketReleaseRate(releaseRate);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                input.next();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input, please enter a number within the range (1-5 minutes)!");
            }
        }

        while (true) {
            try {
                System.out.print("Enter the customer retrieval rate (in seconds): ");
                int retrievalRate = input.nextInt();
                setCustomerRetrievalRate(retrievalRate);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                input.next();
            } catch (IllegalArgumentException e){
                System.out.println("Invalid Input, please enter a number within the range (1-10 seconds)!");
            }
        }

        System.out.println("Configuration complete.");
        System.out.println("Maximum Ticket Capacity = " + getMaxTicketCapacity() + " tickets");
        System.out.println("Total Tickets available in first release : " + getTotalTickets() + " tickets");
        System.out.println("Ticket Release Rate: " + getTicketReleaseRate() + " minutes");
        System.out.println("Customer Retrieval Rate: " + getCustomerRetrievalRate() + " seconds");
    }

    public void startTicketOperations() {
        if (!isRunning) {
            isRunning = true;
            System.out.println("Starting ticket operations...");

            // Thread for ticket release
            new Thread(() -> {
                try {
                    while (isRunning) {
                        synchronized (this) {
                            int remainingCapacity = maxTicketCapacity - (ticketsSold + currentTicketsAvailable);
                            if (remainingCapacity > 0) {
                                int ticketsToAdd = Math.min(300, remainingCapacity);
                                currentTicketsAvailable += ticketsToAdd;
                                System.out.println(ticketsToAdd + " tickets released. Current available tickets: " + currentTicketsAvailable);
                                logTransaction("Released " + ticketsToAdd + " tickets.");
                            } else {
                                System.out.println("Maximum ticket capacity reached. No more tickets will be released.");
                                break;
                            }
                        }
                        Thread.sleep(ticketReleaseRate * 60 * 1000); // Convert minutes to milliseconds
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Ticket release process interrupted.");
                }
            }).start();

            // Thread for customer retrieval
            new Thread(() -> {
                try {
                    while (isRunning) {
                        synchronized (this) {
                            if (currentTicketsAvailable > 0) {
                                int ticketsToBuy = Math.min(1, currentTicketsAvailable); // Customer buys 1 ticket at a time
                                currentTicketsAvailable -= ticketsToBuy;
                                ticketsSold += ticketsToBuy; // Increase the tickets sold count
                                System.out.println("Customer purchased " + ticketsToBuy + " ticket(s). Tickets remaining: " + currentTicketsAvailable);
                                logTransaction("Sold " + ticketsToBuy + " ticket(s).");
                            } else {
                                System.out.println("No tickets available for purchase.");
                                break;
                            }
                        }
                        Thread.sleep(customerRetrievalRate * 1000); // Convert seconds to milliseconds
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Customer retrieval process interrupted.");
                }
            }).start();
        } else {
            System.out.println("Ticket operations are already running.");
        }
    }

    public void stopTicketOperations() {
        if (isRunning) {
            isRunning = false;
            System.out.println("Stopping ticket operations...");
        } else {
            System.out.println("Ticket operations are not currently running.");
        }
    }

    private void logTransaction(String message) {
        System.out.println("Log: " + message);
    }

    public void showTicketOperations() {
        System.out.println("Tickets sold so far: " + ticketsSold);
        System.out.println("Tickets remaining: " + currentTicketsAvailable);
    }

    // Save the configuration as a JSON file
    public void saveConfiguration(String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(this, writer);
            System.out.println("Configuration saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving configuration: " + e.getMessage());
        }
    }

    // Load the configuration from a JSON file
    public static Configuration loadConfiguration(String filename) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filename)) {
            Configuration config = gson.fromJson(reader, Configuration.class);
            System.out.println("Configuration loaded from " + filename);
            return config;
        } catch (IOException e) {
            System.out.println("Error loading configuration: " + e.getMessage());
            return null;
        }
    }
}
