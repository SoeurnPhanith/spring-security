//package com.example.demo_spring_security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity //enable SecurityFilterChain if not use it security config not work
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        return http
//                .csrf(csrf -> csrf.disable())
//                //disable csrf for REST api but if use with web app need enable csrf for session & cookie
//
//                .authorizeHttpRequests(
//                        auth -> auth
//                        //define role who can access with end-point
//
//                         .requestMatchers("/api").permitAll()
//                         // anyone can access this end-point no need login or role
//
//                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
//                        //only admin role can access this end-point
//
//                        .requestMatchers("/api/users/**").hasAnyRole("USER","ADMIN")
//                        //normal user and admin can access this end-point
//
//                        .anyRequest().authenticated()
//                        //  Any other request must be login
//                ).build();
//    }
//
//}
