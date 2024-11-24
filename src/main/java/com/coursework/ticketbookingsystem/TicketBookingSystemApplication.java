package com.coursework.ticketbookingsystem;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class TicketBookingSystemApplication {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int choice = 0;
        while (choice != 3) {
            MainMenu();
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
                    runSpringApplication(args);
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

    private static void MainMenu() {
        System.out.println("\nWelcome to the Ticket Booking System");
        System.out.println("Press 1 for CLI");
        System.out.println("Press 2 for Spring Boot Application");
        System.out.println("Press 3 to Exit");
    }

    private static void runCLI() {
        Configuration config = new Configuration(); // Create a Configuration instance
        config.start(); // Configure system parameters

        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            System.out.print("Enter command (start/stop/show/exit): ");
            command = input.nextLine().trim().toLowerCase();

            Configuration loadedConfig = null;
            switch (command) {
                case "start":
                    loadedConfig = Configuration.loadConfiguration("config.json");
                    config.startTicketOperations();
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

            if (loadedConfig != null) {
                // Display loaded configuration for verification
                System.out.println("Loaded Max Capacity: " + loadedConfig.getMaxTicketCapacity());
                System.out.println("Loaded Available Tickets: " + loadedConfig.getCurrentTicketsAvailable());
                System.out.println("Loaded Tickets Sold: " + loadedConfig.getTicketsSold());
            }

        }



    }


    private static void runSpringApplication(String[] args) {
        System.out.println("Starting Spring Boot Application...");
        SpringApplication.run(TicketBookingSystemApplication.class, args);

    }
}
