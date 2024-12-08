package com.TicketingSystem.TicketingSystem.config;

import com.TicketingSystem.TicketingSystem.model.TicketPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TicketPool ticketPool() {
        return new TicketPool(200);
    }
}

