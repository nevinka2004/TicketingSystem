package com.TicketingSystem.TicketingSystem.controller;

import com.TicketingSystem.TicketingSystem.config.TicketingConfiguration;
import com.TicketingSystem.TicketingSystem.service.TicketingConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class TicketConfigurationController {
    private final TicketingConfigurationService configService;

    @Autowired
    public TicketConfigurationController(TicketingConfigurationService config){
        configService = config;

    }
    @GetMapping
    public ResponseEntity<TicketingConfiguration> getConfiguration(){
        return ResponseEntity.ok(configService.getConfiguration());
    }

    @PostMapping
    public ResponseEntity<Void> updateConfig(@RequestBody TicketingConfiguration newConfig) {
        configService.updateConfiguration(newConfig);
        return ResponseEntity.noContent().build();
        
    }


}
