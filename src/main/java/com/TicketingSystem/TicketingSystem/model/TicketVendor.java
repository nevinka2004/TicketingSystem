package com.TicketingSystem.TicketingSystem.model;

public class TicketVendor implements Runnable {
    private TicketPool ticketPool;
    private int ticketReleaseRate;

    public TicketVendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        try{
            while (!Thread.currentThread().isInterrupted()){
                releaseTickets();
                Thread.sleep(1000/ticketReleaseRate);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Vendor interrupted: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void releaseTickets(){
        ticketPool.addTickets(1);
        System.out.println("Ticket released by vendor.");
    }

}
