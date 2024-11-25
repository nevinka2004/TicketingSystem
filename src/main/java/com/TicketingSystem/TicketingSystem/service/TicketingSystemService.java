package com.TicketingSystem.TicketingSystem.service;

import com.TicketingSystem.TicketingSystem.model.Customer;
import com.TicketingSystem.TicketingSystem.model.TicketPool;
import com.TicketingSystem.TicketingSystem.model.TicketVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Service class to manage ticket vendors and the ticketing system.
@Service
public class TicketingSystemService {
    private final TicketPool ticketPool;
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();

    // Constructor to inject the ticket pool dependency.
    @Autowired
    public TicketingSystemService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Starts a specified number of ticket vendors with a given ticket release rate.
    public void startVendors(int numberOfVendors, int ticketReleaseRate) {
        for (int i = 0; i < numberOfVendors; i++) {
            TicketVendor vendor = new TicketVendor(ticketPool, ticketReleaseRate);
            Thread vendorThread = new Thread(vendor);
            vendorThreads.add(vendorThread);
            vendorThread.start();
        }
    }

    // Stops all active vendor threads by interrupting them and clearing the list.
    public void stopVendors() {
        for (int i = 0; i < vendorThreads.size(); i++) {
            vendorThreads.get(i).interrupt();
        }
        vendorThreads.clear();
    }

    // Starts multiple customer threads to retrieve tickets at a specified rate.
    public void startCustomer(int numberOfCustomers, int customerRetrievalRate) {
        for (int i = 0; i < numberOfCustomers; i++) {
            Customer customer  = new Customer(ticketPool, customerRetrievalRate);
            Thread CustomerThread = new Thread(customer);
            customerThreads.add(CustomerThread);
            CustomerThread.start();
        }
    }

    // Stops all customer threads by interrupting them and clearing the list.
    public void stopCustomer() {
        for (int i = 0; i < customerThreads.size(); i++) {
            customerThreads.get(i).interrupt();
        }
        customerThreads.clear();
    }

    // Returns the current number of active vendor threads.
    public int getVendorCount() {
        return vendorThreads.size();
    }

    // Returns the current number of active customer threads.
    public int getCustomerCount() {
        return customerThreads.size();
    }
}
