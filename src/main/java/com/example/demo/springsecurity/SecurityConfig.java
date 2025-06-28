/** Clasa pentru implementarea securitatii aplicatiei cu spring-security
 * @author Ghirda-Ratoi Melania-Maria
 * @version 12 Ianuarie 2024
 */

package com.example.demo.springsecurity;

import com.example.demo.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Configuration
public class SecurityConfig {

    // Connection to the Service Layer
    private final CustomUserDetailsService usersDetailsService;

    // Constructor
    public SecurityConfig(CustomUserDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }

    @Bean
    public DefaultSecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                // Defines authorised pages that can be accessed without being authenticated
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/h2-console/**", "/register", "/login", "/styles/**", "/home", "/").permitAll()
                        .anyRequest().authenticated()
                )

                // Defines the '/login' page as the login path
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/vacations", true)
                        .permitAll()
                )

                // optional: defines logout path
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/home")
                        .addLogoutHandler(new SecurityContextLogoutHandler())
                        .permitAll()
                )

                // Homepage is the authentication entry point and the access denier in this case.
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) -> {
                            response.sendRedirect("/home");
                        })
                        .accessDeniedHandler((request, response, accessDeniedException) -> {
                            response.sendRedirect("/home");
                        })
                        .defaultAuthenticationEntryPointFor((request, response, authException) -> response.sendRedirect("/home"), request -> true)
                )
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable());

        return http.build();
    }

    // Password encoder using bcrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Authentication Manager deals with the Http Security requests, allowing or blocking access to certain webpages.
    // Uses the current user's credentials.
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }
}
