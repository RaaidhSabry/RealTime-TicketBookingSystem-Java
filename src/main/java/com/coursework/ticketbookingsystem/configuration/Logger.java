package com.coursework.ticketbookingsystem.configuration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class Logger {
    private static final String LOG_FILE = "system.log";

    public static void logToConsole(String message) {
        System.out.println(message);
    }

    public static void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
