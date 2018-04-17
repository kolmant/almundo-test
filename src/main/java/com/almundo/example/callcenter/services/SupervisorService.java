package com.almundo.example.callcenter.services;

public class SupervisorService extends ScalableAssignorService {

    public SupervisorService(AssignorService superior) {
        super(superior);
    }
}
