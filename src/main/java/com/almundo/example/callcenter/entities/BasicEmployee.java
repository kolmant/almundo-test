package com.almundo.example.callcenter.entities;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Emulates the basic employee behavior.
 */
@Setter
@Getter
public class BasicEmployee extends Employee {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicEmployee.class);

    private String name;
    private Lock isBusy;

    public BasicEmployee(String name){
        isBusy = new ReentrantLock();
        this.name = name;
    }

    /**
     * An employee can take a call if he is not busy in another call
     * @param c The call
     * @return true if the employee took the call, false otherwise
     */
    @Override
    public Boolean takeCall(Call c) {
        boolean callTaken = false;
        if (isBusy.tryLock()) {
            try {
                LOGGER.info(c.getName()+" assigned to "+getName());
                assignedCall = c;
                assignedCall.start();
                assignedCall.join();
                assignedCall = null;
                callTaken = true;
            } catch (InterruptedException e) {
                LOGGER.info("An error occurred while assigning "+c.getName()+" to "+toString());
                e.printStackTrace();
            }
            finally {
                isBusy.unlock();
            }
        }

        return callTaken;
    }
}
