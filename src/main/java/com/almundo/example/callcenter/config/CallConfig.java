package com.almundo.example.callcenter.config;

import com.almundo.example.callcenter.utils.CallProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CallConfig {

    @Value("${app.call.duration.min}")
    private Integer minCallDuration;

    @Value("${app.call.duration.max}")
    private Integer maxCallDuration;

    @Bean
    public CallProperties setCallProperties() {
        return new CallProperties(minCallDuration, maxCallDuration);
    }
}
