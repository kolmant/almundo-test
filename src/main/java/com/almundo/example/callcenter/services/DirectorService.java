package com.almundo.example.callcenter.services;

public class DirectorService extends ScalableAssignorService {

    public DirectorService(AssignorService superior) {
        super(superior);
    }
}
