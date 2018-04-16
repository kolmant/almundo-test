package com.almundo.example.callcenter.utils;

import lombok.Getter;

@Getter
public class CallProperties {

    private Integer minDuration;
    private Integer maxDuration;

    public CallProperties(Integer minCallDuration, Integer maxCallDuration) {
        this.minDuration = minCallDuration;
        this.maxDuration = maxCallDuration;
    }
}
