 package com.ssafy.trip.config;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ssafy.trip.util.SessionListener;

@Configuration
public class SessionConfig {
    
    @Bean
    public ServletListenerRegistrationBean myListenerRegistration() {
        ServletListenerRegistrationBean registration = new ServletListenerRegistrationBean(new SessionListener());
        registration.setOrder(1);
        return registration;
    }
}