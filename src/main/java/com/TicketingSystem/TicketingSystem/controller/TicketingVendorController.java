package com.TicketingSystem.TicketingSystem.controller;

import com.TicketingSystem.TicketingSystem.service.TicketingSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendors")
public class TicketingVendorController {
    private final TicketingSystemService ticketingSystemService;

    @Autowired
    public TicketingVendorController(TicketingSystemService ticketingSystemService) {
        this.ticketingSystemService = ticketingSystemService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startVendors(@RequestParam int vendorsCount, @RequestParam int ticketReleaseRate) {
       ticketingSystemService.startVendors(vendorsCount,ticketReleaseRate);
       return ResponseEntity.ok("Vendors started.");
    }


    @PostMapping("/stop")
    public ResponseEntity<String> startVendors() {
        ticketingSystemService.stopVendors();
        return ResponseEntity.ok("Vendors stopped.");
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getVendorCount(){
        return ResponseEntity.ok(ticketingSystemService.getVendorCount());
    }
}
