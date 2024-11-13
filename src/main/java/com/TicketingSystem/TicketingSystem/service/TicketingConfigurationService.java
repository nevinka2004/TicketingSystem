package com.TicketingSystem.TicketingSystem.service;

import com.TicketingSystem.TicketingSystem.config.TicketingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketingConfigurationService {
    @Autowired
    private TicketingConfiguration config;
    public TicketingConfiguration getConfiguration(){
        return config;
    }
    public void updateConfiguration(TicketingConfiguration newConfig){
        config.setTotalTickets(newConfig.getTotalTickets());
        config.setTicketReleaseRate(newConfig.getTicketReleaseRate());
        config.setCustomerRetrievalRate(newConfig.getCustomerRetrievalRate());
        config.setMaxTicketCapacity(newConfig.getMaxTicketCapacity());

    }


}
