package com.almundo.example.callcenter.entities;

public abstract class Employee {
    protected Call assignedCall;

    abstract public Boolean isBusy();
    abstract public void takeCall(Call c);

}
