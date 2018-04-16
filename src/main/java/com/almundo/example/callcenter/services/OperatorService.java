package com.almundo.example.callcenter.services;

public class OperatorService extends ScalableAssignorService {

    public OperatorService(AssignorService superior) {
        super(superior);
    }
}
