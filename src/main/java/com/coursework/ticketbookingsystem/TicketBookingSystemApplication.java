package com.coursework.ticketbookingsystem;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class TicketBookingSystemApplication {

    public static void main(String[] args) {
        startSpringApplication();

        // Main Menu setup
        Scanner input = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            displayMainMenu();
            System.out.println("Enter your choice:");
            try {
                choice = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, please enter a number.");
                input.next();
                continue;
            }

            switch (choice) {
                case 1:
                    runCLI();
                    break;
                case 2:
                    startSpringApplication();
                    break;
                case 3:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\nWelcome to the Ticket Booking System");
        System.out.println("Press 1 for CLI Configuration");
        System.out.println("Press 2 to Run Springboot");
        System.out.println("Press 3 to Exit");
    }

    // CLI logic
    private static void runCLI() {
        Configuration config = new Configuration();
        config.start();

        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Enter command (start/stop/show/exit): ");
            command = input.nextLine().trim().toLowerCase();

            switch (command) {
                case "start":
                    Configuration loadedConfig = Configuration.loadConfiguration("config.json");
                    if (loadedConfig != null) {
                        config.startTicketOperations();
                        System.out.println("Loaded Max Capacity: " + loadedConfig.getMaxTicketCapacity());
                        System.out.println("Loaded Available Tickets: " + loadedConfig.getCurrentTicketsAvailable());
                        System.out.println("Loaded Tickets Sold: " + loadedConfig.getTicketsSold());
                    }
                    break;
                case "stop":
                    config.stopTicketOperations();
                    config.saveConfiguration("config.json");
                    break;
                case "show":
                    config.showTicketOperations();
                    break;
                case "exit":
                    config.stopTicketOperations(); // Stop operations before exiting
                    System.out.println("Exiting CLI...");
                    return;
                default:
                    System.out.println("Unknown command. Please enter 'start', 'stop', 'show' or 'exit'.");
            }
        }
    }

    // SpringBoot logic
    public static void startSpringApplication() {
        System.out.println("Starting Spring Boot Application...");
        try {
            SpringApplication.run(TicketBookingSystemApplication.class);
            System.out.println("Spring Boot Application started successfully.");
        } catch (Exception e) {
            System.out.println("");
            System.out.println("");
            System.out.println("");
            displayMainMenu();
            System.err.println("Enter Configuration !!");
        }
    }
}
