package com.TicketingSystem.TicketingSystem.controller;

import com.TicketingSystem.TicketingSystem.service.TicketingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class TicketingCustomerController {
    private final TicketingSystemService ticketingSystemService;

    @Autowired
    public TicketingCustomerController(TicketingSystemService ticketingSystemService) {
        this.ticketingSystemService = ticketingSystemService;
    }

    //POST /api/customers/start
    @PostMapping("/start")
    public ResponseEntity<String> startCustomer(@RequestParam int customerCount, @RequestParam int customerRetrievalRate) {
        ticketingSystemService.startCustomer(customerCount,customerRetrievalRate);
        return ResponseEntity.ok("Customers started.");
    }

    //POST /api/customers/stop
    @PostMapping("/stop")
    public ResponseEntity<String> stopCustomers() {
        ticketingSystemService.stopCustomer();
        return ResponseEntity.ok("Customers stopped.");
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCustomerCount(){
        return ResponseEntity.ok(ticketingSystemService.getCustomerCount());
    }
}


