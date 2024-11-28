package com.coursework.ticketbookingsystem.vendor;

import com.coursework.ticketbookingsystem.configuration.Configuration;
import com.coursework.ticketbookingsystem.ticketpool.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class VendorService {
    @Autowired
    private TicketPool ticketPool;

    @PostConstruct
    public void startVendors() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            Vendor vendor = new Vendor(i + 1 , ticketPool); // Example parameters
            new Thread(vendor).start();
        }
        Thread.sleep(Configuration.getTicketReleaseInterval());

    }
}
