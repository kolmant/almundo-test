package com.almundo.example.callcenter.services;

import com.almundo.example.callcenter.entities.Call;
import com.almundo.example.callcenter.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssignorService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private List<Employee> employees;

    public AssignorService(){
        employees = Collections.synchronizedList(new ArrayList<>());
    }

    public boolean assignCall(Call c) {
        LOGGER.info("Assigning "+c.getName()+"...");
        Employee e;
        boolean callTaken = false;
        for (int i = 0 ; i < employees.size() && !callTaken; i++) {
            if (!(e = employees.get(i)).isBusy()) {
                LOGGER.info(c.getName()+" assigned to employee number "+i);
                e.takeCall(c);
                callTaken = true;
            }
        }
        if(!callTaken) {
            LOGGER.info("I'm busy. "+c.getName()+" cannot be assigned.");
        }

        return callTaken;
    }

    public Employee add(Employee e){
        employees.add(e);
        return e;
    }
}
