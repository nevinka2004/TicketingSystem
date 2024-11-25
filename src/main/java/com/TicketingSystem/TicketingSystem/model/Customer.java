package com.TicketingSystem.TicketingSystem.model;

public class Customer implements Runnable {
    private TicketPool ticketPool;
    private int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate){
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                purchaseTicket();
                Thread.sleep(1000 / customerRetrievalRate);
            }
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Customer interrupted: "+ e.getMessage());
            }
        }

    private void purchaseTicket(){
        String ticket = ticketPool.removeTicket();
        if (ticket !=null){
            System.out.println("Ticket purchased by customer: " + ticket);
        } else {
            System.out.println("No tickets available for purchase.");
        }
    }
    }


