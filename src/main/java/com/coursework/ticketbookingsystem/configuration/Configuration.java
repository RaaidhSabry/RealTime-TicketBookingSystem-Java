package com.coursework.ticketbookingsystem.configuration;

import com.coursework.ticketbookingsystem.customer.Customer;
import com.coursework.ticketbookingsystem.vendor.Vendor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Configuration {
    public static int maxTicketCapacity;
    public static int ticketReleaseRate;
    public static int customerRetrievalRate;
    private int totalTickets;
    private boolean isRunning = false;
    public static int currentTicketsAvailable;
    private int ticketsSold = 0;

    public Configuration() {}

    public int getTotalTickets() {
        return totalTickets;
    }

    //validation for total tickets
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

    // validation for ticket release rate
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

    // validation for customer retrieval rate
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        if (customerRetrievalRate > 0 && customerRetrievalRate <= 10) {
            this.customerRetrievalRate = customerRetrievalRate;
        } else {
            throw new IllegalArgumentException("The customer retrieval rate should be between 1 and 10 minutes.");
        }
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    // validation for max ticket capacity
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        if (maxTicketCapacity > 0 && maxTicketCapacity <= 5000) {
            Configuration.maxTicketCapacity = maxTicketCapacity;
        } else {
            throw new IllegalArgumentException("The max ticket capacity should be between 1 and 5000 tickets.");
        }
    }

    public int getCurrentTicketsAvailable() {
        return currentTicketsAvailable;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    //release interval
    public static int getTicketReleaseInterval() {
        return ticketReleaseRate * 60 * 1000;
    }


    //retrieval interval
    public static int getCustomerRetrievalInterval() {
        return customerRetrievalRate * 1000;
    }

    @Override
    public String toString() {
        return "Configuration {" + "Max Ticket Capacity=" + maxTicketCapacity + ", Current Tickets Available=" + currentTicketsAvailable + ", Tickets Sold=" + ticketsSold + '}';
    }

    public void start() {
        System.out.println("Running the Command Line Interface (CLI)...");

        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Enter the Maximum Capacity for the event : ");
                int maxTicketCapacity = input.nextInt();
                setMaxTicketCapacity(maxTicketCapacity);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number. ");
                input.next();
            } catch (IllegalArgumentException e) {
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
                System.out.println("Invalid Input, please enter a number within the Maximum Ticket Capacity! .... Maximum Ticket Capacity = " + maxTicketCapacity);
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
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid Input, please enter a number within the range (1-10 seconds)!");
            }
        }

        System.out.println("Configuration complete:");
        System.out.println(this);
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
                                int ticketsToAdd = Math.min(Vendor.ticketsPerRelease, remainingCapacity);
                                currentTicketsAvailable += ticketsToAdd;
                                System.out.println(this);
                                logTransaction("Released " + ticketsToAdd + " tickets.");
                            } else {
                                System.out.println("Maximum ticket capacity reached. No more tickets will be released.");
                                break;
                            }
                        }
                        Thread.sleep(getTicketReleaseInterval());
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
                                int ticketsToBuy = Math.min(Customer.ticketsToPurchase, currentTicketsAvailable);
                                currentTicketsAvailable -= ticketsToBuy;
                                ticketsSold += ticketsToBuy;
                                System.out.println(this);
                                logTransaction("Sold " + ticketsToBuy + " ticket(s).");
                            } else {
                                System.out.println("No tickets available for purchase.");
                                break;
                            }
                        }
                        Thread.sleep(getCustomerRetrievalInterval());
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
        System.out.println(this);
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
