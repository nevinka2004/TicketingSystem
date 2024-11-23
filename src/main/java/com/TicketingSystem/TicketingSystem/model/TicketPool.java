package com.TicketingSystem.TicketingSystem.model;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


// Represents a ticket pool system that manages ticket creation, distribution, and logging.
@Component
public class TicketPool {
    // Synchronized list to hold tickets ensuring thread safety.
    private final List<String> tickets = Collections.synchronizedList(new ArrayList<>());
    private int maxCapacity;
    private SseEmitter emitter;

    // Default constructor with a maximum capacity of 200 tickets.
    public TicketPool() {
        this.maxCapacity = 200;
        this.emitter = new SseEmitter(Long.MAX_VALUE);
    }

    // Constructor to set a custom maximum ticket capacity.
    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    // Adds a specified number of tickets to the pool if space is available.
    public synchronized void addTickets(int count) {
        if (tickets.size() + count <= maxCapacity) {
            for (int i = 0; i < count; i++) {
                tickets.add("Ticket-" + (tickets.size() + 1));
            }
            sendLog(count + " ticket(s) added to the pool. Total tickets: " + tickets.size());
        } else {
            sendLog("Ticket pool reached maximum capacity. Cannot add more tickets.");
        }
    }

    // Removes and returns a ticket from the pool if available, or logs if the pool is empty.
    public synchronized String removeTicket() {
        if (tickets.isEmpty()) {
            sendLog("No tickets available in the pool.");
            return null;
        } else {
            String ticket = tickets.remove(0);
            sendLog("Ticket purchased. Remaining tickets: " + tickets.size());
            return ticket;
        }
    }

    // Returns the current number of tickets in the pool.
    public synchronized int getTicketCount() {
        return tickets.size();
    }

    // Creates a new SSE emitter for sending real-time updates and handles its lifecycle.
    public synchronized SseEmitter createEmiiter() {
        this.emitter = new SseEmitter(Long.MAX_VALUE);
        this.emitter.onCompletion(() -> this.emitter = null);
        this.emitter.onTimeout(() -> this.emitter = null);
        return this.emitter;
    }

    // Sends a log message to the connected client via the emitter.
    private void sendLog(String message) {
        if (emitter != null) {
            try {
                emitter.send(SseEmitter.event().data(message));
            } catch (IOException e) {
                e.printStackTrace();
                emitter = null;
            } catch (Exception e1) {
                e1.printStackTrace();
                emitter = null;
            }
        }
    }
}
