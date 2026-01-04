package com.example.Spring_Security_OAuth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        auth->auth
                       // .requestMatchers("/","/login").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(Customizer.withDefaults()); //enable OAuth2
               //default UI
        return http.build();

    }

}
