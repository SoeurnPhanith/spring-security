package com.example.spring_security_inRAM_part2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //not encrypt password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //Load user(admin) or assign user who is admin
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails admin = User
                .builder()
                .username("Soeurn Phanith")
                .password("admin@123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }


    //authorization on accessing endpoint
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(authorization -> authorization
                        // public
                        .requestMatchers("/api/register").permitAll()

                        // admin only
                        .requestMatchers("/api/auth/admin/**").hasRole("ADMIN")

                        // user + admin
                        .requestMatchers("/api/products").hasAnyRole("USER", "ADMIN")

                        // must be login
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {})
                .build();
    }

}
