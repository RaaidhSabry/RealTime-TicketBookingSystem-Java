package com.coursework.ticketbookingsystem.customer;

import com.coursework.ticketbookingsystem.ticketpool.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class CustomerService {
    @Autowired
    private TicketPool ticketPool;

    @PostConstruct
    public void startCustomers() {
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer(i + 1, 5, 1000, ticketPool); // Example parameters
            new Thread(customer).start();
        }
    }
}
