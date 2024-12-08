package com.TicketingSystem.TicketingSystem.controller;

import com.TicketingSystem.TicketingSystem.model.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class LogController {
    private final TicketPool ticketPool;

    @Autowired
    public LogController(TicketPool ticketPool){
        this.ticketPool=ticketPool;
    }

    @GetMapping("/api/logs/stream")
    public SseEmitter streamLogs(){
        return ticketPool.createEmiiter();
    }

}
