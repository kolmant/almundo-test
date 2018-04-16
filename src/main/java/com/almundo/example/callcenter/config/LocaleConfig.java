package com.almundo.example.callcenter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Configuration
public class LocaleConfig {
    @Value("${app.locale}")
    private String locale;

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source =  new ResourceBundleMessageSource();
        source.setBasename("locale/messages");
        source.setDefaultEncoding("UTF-8");
        Locale.setDefault(new Locale(locale));

        return source;
    }
}
