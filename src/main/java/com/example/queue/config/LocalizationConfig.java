package com.example.queue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class LocalizationConfig {

    @Bean
    public LocaleResolver localeResolver(){
        AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
//        sessionLocaleResolver.setDefaultLocale(Locale.US);
        return  sessionLocaleResolver;
    }
}
