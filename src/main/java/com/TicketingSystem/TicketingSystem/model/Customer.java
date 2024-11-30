package com.TicketingSystem.TicketingSystem.model;


// Implements the Runnable interface to allow concurrent execution.
public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // Method that runs when the thread for this customer starts.
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                purchaseTicket();
                Thread.sleep(1000 / customerRetrievalRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer interrupted: " + e.getMessage());
        }
    }

    // Method for attempting to purchase a ticket from the ticket pool.
    private void purchaseTicket() {
        String ticket = ticketPool.removeTicket();
        if (ticket != null) {
            System.out.println("Ticket purchased by customer: " + ticket);
        } else {
            System.out.println("No tickets available for purchase.");
        }
    }
}
