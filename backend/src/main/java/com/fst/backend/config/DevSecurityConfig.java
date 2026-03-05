package com.fst.backend.config;

import com.fst.backend.security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class DevSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public DevSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

     // In early development, security is disabled for easier testing
     // ToDo: Implement proper security configuration for development environment

     @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 // CSRF configuration
                 .csrf(csrf -> csrf
                         .ignoringRequestMatchers("/h2-console/**") // H2 console
                         .disable() // disable globally for POST /graphql etc.
                 )
                 // CORS default
                 .cors(Customizer.withDefaults())
                 // Headers
                 .headers(headers -> headers
                         .frameOptions(frame -> frame.disable()) // disable X-Frame-Options for H2 console
                 )
                 // Authorization rules
                 .authorizeHttpRequests(auth -> auth
                         // H2 console
                         .requestMatchers("/h2-console/**").permitAll()
                         // Auth endpoints
                         .requestMatchers("/api/v1/auth/**").permitAll()
                         .requestMatchers("/api/v1/**").permitAll()
                         // GraphQL
                         .requestMatchers(HttpMethod.POST, "api/graphql").permitAll()
                         .requestMatchers(HttpMethod.GET, "api/graphql").permitAll()
                         // Everything else
                         //.anyRequest().authenticated()
                         .anyRequest().permitAll()
                 )
                 // Add JWT filter BEFORE UsernamePasswordAuthenticationFilter
                 .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

         return http.build();
     }
}
