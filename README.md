# Ticket Booking System

A multi-threaded ticket booking system that supports real-time ticket management, configuration, and monitoring. This project is implemented using a Java CLI for core functionalities, a Spring Boot backend, and an Angular-based frontend GUI.

### Table of Contents
- [Project Overview](#Project_Overview)
- [Features](#Features)
- [Technologies Used](#Technologies_Used)
- [System Architecture](#System_Architecture)
- [Installation and Setup](#Installation_and_Setup)
- [How to Use](#How_to_Use)
- [Authors](#Authors)

## Project_Overview

The Ticket Booking System is a producer-consumer-based application that allows vendors to add tickets to a shared ticket pool and customers to retrieve tickets in real time. It uses synchronization techniques to ensure thread safety and provides a graphical user interface for better user experience.

## Features

### Core Features

- System Configuration: Configure total tickets, ticket release rate, customer retrieval rate, and maximum ticket capacity.
- Producer-Consumer Pattern: Utilizes Java threading for vendors and customers accessing the shared ticket pool.
- Real-Time Monitoring: Displays the ticket pool status and logs all ticket transactions.
- Frontend GUI: Angular-based interface for configuration, real-time status display, and control.
- Backend API: Spring Boot RESTful API for ticket operations and data management.

### Additional Features

- Input validation for configuration settings.
- Real-time synchronization between CLI, backend and frontend.
- Logs all activities for audit and debugging.

## Technologies_Used

### Backend

- Java CLI: Core system implementation.
- Spring Boot: Backend logic and API development.
- JSON: Data persistence for user and ticket details.

### Frontend

- Angular: GUI development for real-time monitoring and control.

### Other Tools

- Git: Version control.

## System_Architecture

### Java CLI:

- Handles ticket system configuration and multi-threaded operations.

### Backend (Spring Boot):

- Manages API endpoints for ticket operations and data updates.
- Stores persistent data in JSON files.

### Frontend (Angular):

- Provides a user-friendly interface for interacting with the system.

## 📷 Screenshots

<p align="center">
  <img src="https://github.com/user-attachments/assets/332e10e0-6e87-47e5-a2d9-ff2b675f5114" alt="Screenshot 1" width="70%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/53724eb8-c346-4092-827e-f83517fb566b" alt="Screenshot 2" width="70%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/749a723b-f8a3-4a1c-bc14-cba6b7802bf6" alt="Screenshot 3" width="70%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/0e249ac0-3532-4c37-9177-a30cde57d524" alt="Screenshot 4" width="70%">
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/8472adb6-129e-41f7-84a7-ea4ccb8a50fb" alt="Screenshot 5" width="70%">
</p>


## Installation_and_Setup

### Prerequisites

- Java 17 or later
- Maven: For building the Spring Boot backend.
- Node.js and npm: For Angular frontend.
- Git: For cloning the repository.

### Steps

- Clone the Repository

##
    git clone https://github.com/your-repo/ticketBookingSystem.git

### Backend Setup

- Navigate to the backend folder:

##
    cd ticketBookingSystem

- Build and run the Spring Boot application:

##
    mvn clean install
    mvn spring-boot:run

### Frontend Setup

- Install all the angular set-up before progressing to the next stage
  
##
    npm install -g @angular/cli
    npm install --save-dev @angular-devkit/build-angular
    npm install @angular/animations @angular/common @angular/compiler @angular/core @angular/forms @angular/platform-browser @angular/platform-browser-dynamic @angular/router @angular/cli @angular/material @angular/cdk @angular/animations --save-dev
    npm install @angular/platform-server @angular/ssr --save-dev


- Navigate to the Angular frontend folder:

##
    cd ticket-booking-system
    
- Install dependencies and start the Angular development server:

##
    npm install
    ng serve
    
### Access the Application

- Open your browser and navigate to:

##
    http://localhost:4200

### How to Use CLI

- Configure system parameters (e.g., total tickets, release rate).
- Use start and stop commands to control operations.
- Monitor real-time ticket status in the console.

### Frontend GUI

- Access the control panel for starting/stopping the customer - vendor pattern.
- View ticket pool status in the homepage, you can refresh the browser to get real-time updates.
- Sign-up as a customer or a vendor for more dynamic operations.
- Login as a customer to buy tickets from the front-end
- Login as a vendor to update settings dynamically via the configuration panel.
- The "DecemberFest" logo acts as a hyperlink to the homepage.

### Backend API

Access RESTful endpoints for advanced operations:

- Buy tickets: POST /api/tickets/customer/buy
- Ticket Status: POST /api/tickets/info
- etc...

## Authors

This README file provides all necessary details for setup, usage and understanding of the project.

M.Raaidh Sabry <br>
Cheers !!







