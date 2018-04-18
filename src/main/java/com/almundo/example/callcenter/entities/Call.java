package com.almundo.example.callcenter.entities;

import com.almundo.example.callcenter.utils.CallProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

/**
 * Simulates a call.
 */
public class Call extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(Call.class);

    private static final Random random = new Random();

    private Integer callDuration;

    /**
     * Creates a call and calculates its duration
     * @param name The call identifier
     * @param prop The call properties (@see com.almundo.example.callcenter.utils.CallProperties)
     */
    public Call(String name, CallProperties prop) {
        super(name);
        callDuration = random.nextInt(prop.getMaxDuration()-prop.getMinDuration()) + prop.getMinDuration();
    }

    @Override
    public void run() {
        String callIdentifier = Thread.currentThread().getName();
        LOGGER.info(callIdentifier +" started.");
        try {
            Thread.sleep(callDuration);
        } catch (InterruptedException e) {
            LOGGER.warn("Problem running "+ callIdentifier +": "+e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info(callIdentifier +" ended. Duration: "+callDuration+" ms.");
    }
}
