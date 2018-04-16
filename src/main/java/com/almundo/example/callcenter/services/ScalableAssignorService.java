package com.almundo.example.callcenter.services;

import com.almundo.example.callcenter.entities.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScalableAssignorService extends AssignorService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private AssignorService superior;

    public ScalableAssignorService(AssignorService superior){
        super();
        this.superior = superior;
    }

    public boolean assignCall(Call c) {
        LOGGER.info("Assigning "+c.getName()+"...");
        boolean callTaken = super.assignCall(c);
        if (!callTaken) {
            LOGGER.info("I'm busy. Scaling "+c.getName()+"...");
            callTaken = superior.assignCall(c);
        }
        LOGGER.info("Call: "+c.getName()+" ended.");

        return callTaken;
    }
}
