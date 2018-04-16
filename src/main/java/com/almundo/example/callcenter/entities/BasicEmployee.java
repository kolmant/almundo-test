package com.almundo.example.callcenter.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class BasicEmployee extends Employee {

    @Override
    public Boolean isBusy() {
        return assignedCall != null;
    }

    @Override
    public void takeCall(Call c) {
        synchronized (this) {
            assignedCall = c;
            assignedCall.start();
            try {
                assignedCall.join();
                assignedCall = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
