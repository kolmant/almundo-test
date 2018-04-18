package com.almundo.example.callcenter.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Manages the operators employees.
 *
 * It extends ScalableAssignorService because it needs to scale a call
 * if the service does not have free employees to assign it.
 */
@Service
public class OperatorService extends ScalableAssignorService {

    public OperatorService(@Qualifier("supervisorService") AssignorService superior) {
        super(superior);
    }
}
