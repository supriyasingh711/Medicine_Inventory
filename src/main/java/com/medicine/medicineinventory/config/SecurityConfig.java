package com.medicine.medicineinventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails staff = User.builder()
                .username("staff")
                .password("{noop}test123")
                .roles("STAFF")
                .build();

        UserDetails pharmacist = User.builder()
                .username("pharmacist")

                .password("{noop}test123")
                .roles("PHARMACIST")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}test123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(staff, pharmacist, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        // Public pages (login etc.)
                        .requestMatchers("/showMyLoginPage", "/css/**", "/js/**").permitAll()

                        // Admin access
                        .requestMatchers("/medicines/delete/**", "/admin/**").hasRole("ADMIN")

                        // Pharmacist + Admin access
                        .requestMatchers("/medicines/add", "/medicines/update/**", "/medicines/restock", "/stock/**").hasAnyRole("PHARMACIST", "ADMIN")

                        // All roles (including STAFF) can view list/dashboard
                        .requestMatchers("/medicine", "/dashboard").hasAnyRole("STAFF", "PHARMACIST", "ADMIN")

                        // Any other request requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
