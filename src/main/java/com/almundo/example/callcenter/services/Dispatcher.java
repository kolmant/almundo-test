package com.almundo.example.callcenter.services;

import com.almundo.example.callcenter.entities.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Dispatches incoming calls.
 */
@Service
public class Dispatcher  {

    @Value("${app.call.retries.max}")
    private Integer maxRetries;

    @Autowired
    private OperatorService service;

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Tries to dispatch a call. If a call was not assigned, it will
     * retry to assign it <i>maxRetries</i> times.
     * @param c The call to be dispatched.
     * @return true if the call was assigned, false otherwise
     */
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
