package com.almundo.example.callcenter.entities;

/**
 * Employee abstraction.
 *
 * An employee has an assigned call and can take a call if he is not busy.
 */
public abstract class Employee {
    Call assignedCall;

    abstract public Boolean takeCall(Call c);
}
