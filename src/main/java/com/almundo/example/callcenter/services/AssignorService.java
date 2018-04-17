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

    public Employee add(Employee e){
        employees.add(e);
        return e;
    }
}
