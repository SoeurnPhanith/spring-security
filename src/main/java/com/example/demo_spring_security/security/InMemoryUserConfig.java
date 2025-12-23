package com.example.demo_spring_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUserConfig {

    /*  UserDetailsService គឺជា interface ដែល Spring Security ប្រើ
         ដើម្បី "load user information" ពេល user login
        (username, password, roles/authorities)
    */
    @Bean
    public UserDetailsService userDetailsService() {
        /*
             UserDetails គឺជា object ដែលផ្ទុក
             username, password, roles (authorities)
             Spring Security ប្រើវា ដើម្បី authenticate & authorize user
        */
        UserDetails user = User.withDefaultPasswordEncoder() //encoder password
                .username("Tin ass")
                .password("tin@123")
                .roles("USER")
                .build();

        //admin account
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("phanith")
                .password("admin@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
        //InMemoryUserDetailManager use for store users in RAM and validate login
    }
}
