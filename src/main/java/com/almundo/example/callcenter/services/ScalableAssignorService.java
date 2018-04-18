package com.almundo.example.callcenter.services;

import com.almundo.example.callcenter.entities.Call;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements the Scalable Assignor behaviour, it means that
 * it will try to assign an incoming call to any of its employees
 * and if nobody is free it will scale the request to its superior.
 */
public class ScalableAssignorService extends AssignorService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private AssignorService superior;

    public ScalableAssignorService(AssignorService superior){
        super();
        this.superior = superior;
    }

    /**
     * Try to dispatch a call. If a call was not assigned,
     * it will scale the request to its superior.
     * @param c The call to be dispatched.
     * @return true if the call was assigned, false otherwise
     */
    @Override
    public boolean dispatchCall(Call c) {
        boolean callTaken = super.dispatchCall(c);
        if (!callTaken) {
            LOGGER.info("I'm busy. Scaling "+c.getName()+"...");
            callTaken = superior.dispatchCall(c);
        }

        return callTaken;
    }
}
