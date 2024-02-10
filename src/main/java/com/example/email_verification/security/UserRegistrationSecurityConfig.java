package com.example.email_verification.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class UserRegistrationSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> {
                    req.requestMatchers("/register/**").permitAll();
//                    req.anyRequest().authenticated();
                })
                .authorizeHttpRequests(req ->
                        req.requestMatchers("/users/**").hasAnyAuthority("USER", "ADMIN").anyRequest().authenticated())
//                .sessionManagement(sessionAuthStrategy ->{
//                    sessionAuthStrategy.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);})

                .formLogin(formLogin -> formLogin.permitAll())
                .logout(logout -> logout.permitAll())
                .build();


     }
}
