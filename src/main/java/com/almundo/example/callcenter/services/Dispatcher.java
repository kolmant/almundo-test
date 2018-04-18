package com.almundo.example.callcenter.services;

import com.almundo.example.callcenter.entities.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Dispatcher  {

    @Value("${app.call.retries.max}")
    private Integer maxRetries;

    @Autowired
    private OperatorService service;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public boolean dispatchCall(Call c) {
        Integer numberOfRetries = 0;
        boolean callTaken = false;
        while (numberOfRetries < maxRetries && !callTaken){
            callTaken = service.dispatchCall(c);
            if (!callTaken){
                LOGGER.info(c.getName()+" was not assigned. Retrying in 1000ms...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    LOGGER.info("An error occurred while retrying "+c.getName());
                    e.printStackTrace();
                }
                numberOfRetries++;
            }
        }

        return callTaken;
    }
}
