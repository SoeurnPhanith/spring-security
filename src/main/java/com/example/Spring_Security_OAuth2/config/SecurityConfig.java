package com.example.Spring_Security_OAuth2.config;

import com.example.Spring_Security_OAuth2.handler.OAuth2LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    OAuth2LoginSuccessHandler oathSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){

        http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(
                        auth->auth
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oathSuccessHandler)
                ); //enable OAuth2
        return http.build();

    }

}
