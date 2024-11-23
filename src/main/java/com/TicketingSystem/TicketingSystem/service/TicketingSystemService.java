package com.TicketingSystem.TicketingSystem.service;

import com.TicketingSystem.TicketingSystem.model.TicketPool;
import com.TicketingSystem.TicketingSystem.model.TicketVendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketingSystemService {
    private final TicketPool ticketPool;
    private final List<Thread> vendorThreads = new ArrayList<>();

    @Autowired
    public TicketingSystemService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }


    public void startVendors (int numberOfVendors, int ticketReleaseRate) {
      for (int i = 0; i< numberOfVendors; i++){
          TicketVendor vendor = new TicketVendor(ticketPool,ticketReleaseRate);
          Thread vendorThread = new Thread(vendor);
          vendorThreads.add(vendorThread);
          vendorThread.start();
      }
    }

    public void stopVendors() {
        for (int i = 0; i< vendorThreads.size(); i++){
            vendorThreads.get(i).interrupt();
        }
        vendorThreads.clear();
    }

    public int getVendorCount() {
        return vendorThreads.size();
    }



}
