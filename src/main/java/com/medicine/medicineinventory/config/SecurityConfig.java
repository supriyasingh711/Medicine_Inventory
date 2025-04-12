package com.medicine.medicineinventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception{
       return  http
                .authorizeHttpRequests(auth->auth
                        .anyRequest().authenticated())
                .httpBasic(withDefaults())

                .csrf(csrf->csrf.disable())
                .build();
    }
    private static  org.springframework.security.config.Customizer<org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer<HttpSecurity>> withDefaults(){
        return org.springframework.security.config.Customizer.withDefaults();
    }
}
