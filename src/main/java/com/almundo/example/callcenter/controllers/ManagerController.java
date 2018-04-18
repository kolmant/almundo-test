package com.almundo.example.callcenter.controllers;

import com.almundo.example.callcenter.Router;
import com.almundo.example.callcenter.entities.Call;
import com.almundo.example.callcenter.services.Dispatcher;
import com.almundo.example.callcenter.utils.CallProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping(value = Router.BASE_PATH, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class ManagerController {

    static final AtomicInteger numberOfCalls = new AtomicInteger(0);

    @Autowired
    private Dispatcher dispatcher;

    @Autowired
    private CallProperties properties;

    @GetMapping(value = Router.TAKE_CALL)
    public ResponseEntity<Boolean> takeCall() {
        return new ResponseEntity<>(dispatcher.dispatchCall(new Call("Call "+numberOfCalls.addAndGet(1), properties)), HttpStatus.OK);
    }
}
