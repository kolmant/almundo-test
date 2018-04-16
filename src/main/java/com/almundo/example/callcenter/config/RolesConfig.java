package com.almundo.example.callcenter.config;

import com.almundo.example.callcenter.entities.BasicEmployee;
import com.almundo.example.callcenter.services.DirectorService;
import com.almundo.example.callcenter.services.OperatorService;
import com.almundo.example.callcenter.services.SuperiorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfig {

    @Bean
    public OperatorService setOperatorService(@Qualifier("directorService") DirectorService service) {
        OperatorService operatorService = new OperatorService(service);
        for(int i = 0 ; i < 10; i++){
            operatorService.add(new BasicEmployee());
        }

        return operatorService;
    }

    @Bean(name = "directorService")
    public DirectorService setDirectorService(@Qualifier("superiorService") SuperiorService service) {
        return new DirectorService(service);
    }

    @Bean(name = "superiorService")
    public SuperiorService setSuperiorService() {
        return new SuperiorService();
    }
}
