package com.TicketingSystem.TicketingSystem.model;

// Represents a ticket vendor that periodically adds tickets to a ticket pool.
public class TicketVendor implements Runnable {
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    // Constructor to initialize the ticket pool and release rate.
    public TicketVendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    // Defines the behavior of the ticket vendor when running in a thread.
    @Override
    public void run() {
        try {
            // Continuously release tickets unless the thread is interrupted.
            while (!Thread.currentThread().isInterrupted()) {
                releaseTickets();
                Thread.sleep(1000 / ticketReleaseRate);
            }
        } catch (InterruptedException e) {
            // Handle thread interruption and clean up.
            Thread.currentThread().interrupt();
            System.out.println("Vendor interrupted: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Adds one ticket to the ticket pool and logs the action.
    private void releaseTickets() {
        ticketPool.addTickets(1);
        System.out.println("Ticket released by vendor.");
    }
}
