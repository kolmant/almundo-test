package com.almundo.example.callcenter.services;

import com.almundo.example.callcenter.entities.BasicEmployee;
import com.almundo.example.callcenter.entities.Call;
import com.almundo.example.callcenter.entities.Employee;
import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Implements the non-scalable Assignor behaviour, it means that
 * it will try to assign an incoming call to any of its employees
 * and if nobody is free it will not scale the request to its superior
 * because it does not have a superior.
 */
@Service
public class AssignorService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private List<Employee> employees;

    public AssignorService(){
        employees = Collections.synchronizedList(new ArrayList<>());
        Faker informationSupplier = new Faker();
        for(int i = 0 ; i < 10; i++){
            employees.add(new BasicEmployee(informationSupplier.name().fullName()));
        }
    }

    public boolean dispatchCall(Call c) {
        LOGGER.info("Assigning "+c.getName()+"...");
        boolean callTaken = false;
        for (int i = 0 ; i < employees.size() && !callTaken; i++) {
            callTaken = employees.get(i).takeCall(c);
        }
        if(!callTaken) {
            LOGGER.info("I'm busy. "+c.getName()+" cannot be assigned.");
        }

        return callTaken;
    }
}
