package com.almundo.example.callcenter.config;

import com.almundo.example.callcenter.entities.BasicEmployee;
import com.almundo.example.callcenter.services.AssignorService;
import com.almundo.example.callcenter.services.DirectorService;
import com.almundo.example.callcenter.services.OperatorService;
import com.almundo.example.callcenter.services.SupervisorService;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfig {

    @Autowired
    private Faker faker;

    @Bean
    public OperatorService setOperatorService(@Qualifier("supervisorService") SupervisorService service) {
        return (OperatorService) fillEmployees(new OperatorService(service));
    }

    @Bean(name = "supervisorService")
    public SupervisorService setSuperiorService(@Qualifier("directorService") DirectorService service) {
        return (SupervisorService) fillEmployees(new SupervisorService(service));
    }

    @Bean(name = "directorService")
    public DirectorService setDirectorService() {
        return (DirectorService) fillEmployees(new DirectorService());
    }

    private AssignorService fillEmployees(AssignorService service){
        for(int i = 0 ; i < 10; i++){
            service.add(new BasicEmployee(faker.name().fullName()));
        }
        return service;
    }
}
