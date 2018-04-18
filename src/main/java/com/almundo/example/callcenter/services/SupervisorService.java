package com.almundo.example.callcenter.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Manages the Superior employee handler role.
 */
@Service("supervisorService")
public class SupervisorService extends ScalableAssignorService {

    public SupervisorService(@Qualifier("directorService") AssignorService superior) {
        super(superior);
    }
}
