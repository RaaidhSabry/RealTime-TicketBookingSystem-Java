# Ticket Booking System

A multi-threaded ticket booking system that supports real-time ticket management, configuration, and monitoring. This project is implemented using a Java CLI for core functionalities, a Spring Boot backend, and an Angular-based frontend GUI.

### Table of Contents
[Project Overview](#Project_Overview)
[Features](#Features)
[Technologies Used](#Technologies_Used)
[System Architecture](#System_Architecture)
[Installation and Setup](#Installation_and_Setup)
[How to Use](#How_to_Use)
[Authors](#License)

## Project_Overview

The Ticket Booking System is a producer-consumer-based application that allows vendors to add tickets to a shared ticket pool and customers to retrieve tickets in real time. It uses synchronization techniques to ensure thread safety and provides a graphical user interface for better user experience.

## Features

Core Features

System Configuration: Configure total tickets, ticket release rate, customer retrieval rate, and maximum ticket capacity.
Producer-Consumer Pattern: Utilizes Java threading for vendors and customers accessing the shared ticket pool.
Real-Time Monitoring: Displays the ticket pool status and logs all ticket transactions.
Frontend GUI: Angular-based interface for configuration, real-time status display, and control.
Backend API: Spring Boot RESTful API for ticket operations and data management.
Additional Features
Input validation for configuration settings.
Real-time synchronization between CLI, backend, and frontend.
Logs all activities for audit and debugging.

## Technologies_Used

Backend

Java CLI: Core system implementation.
Spring Boot: Backend logic and API development.
JSON: Data persistence for user and ticket details.

Frontend

Angular: GUI development for real-time monitoring and control.
RxJS: State management for real-time updates.
Other Tools
Multi-threading: For producer-consumer logic.
Synchronization: Ensures thread-safe operations on shared resources.
Git: Version control.

## System_Architecture
The project follows a modular architecture:

Java CLI:

Handles ticket system configuration and multi-threaded operations.

Backend (Spring Boot):

Manages API endpoints for ticket operations and data updates.
Stores persistent data in JSON files.

Frontend (Angular):

Provides a user-friendly interface for interacting with the system.

## Installation_and_Setup

Prerequisites

Java 17 or later
Maven: For building the Spring Boot backend.
Node.js and npm: For Angular frontend.
Git: For cloning the repository.

Steps

Clone the Repository
bash
Copy code
git clone https://github.com/your-repo/ticket-booking-system.git
cd ticket-booking-system
Backend Setup

Navigate to the backend folder:
bash
Copy code
cd backend
Build and run the Spring Boot application:
bash
Copy code
mvn clean install
mvn spring-boot:run
Frontend Setup

Navigate to the Angular frontend folder:
bash
Copy code
cd frontend
Install dependencies and start the Angular development server:
bash
Copy code
npm install
ng serve
Run the CLI

Navigate to the Java CLI folder:
bash
Copy code
cd cli
Compile and run the CLI application:
bash
Copy code
javac *.java
java Main
Access the Application

Open your browser and navigate to:
arduino
Copy code
http://localhost:4200
How to Use
CLI

Configure system parameters (e.g., total tickets, release rate).
Use start and stop commands to control operations.
Monitor real-time ticket status in the console.
Frontend GUI

Access the control panel for starting/stopping operations.
View ticket pool status, logs, and system configuration.
Update settings dynamically via the configuration panel.
Backend API

Access RESTful endpoints for advanced operations:
Add tickets: POST /api/tickets/add
Remove tickets: POST /api/tickets/remove
View status: GET /api/tickets/status


## License
This project is licensed under the MIT License. See the LICENSE file for details.

This README file provides all necessary details for setup, usage, and understanding your project. Customize it further with your specifics!







