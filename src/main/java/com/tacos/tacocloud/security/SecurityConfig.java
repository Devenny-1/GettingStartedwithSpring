package com.tacos.tacocloud.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.tacos.tacocloud.data.UserRepository;

import com.tacos.tacocloud.User;

import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public interface UserDetailsService {
        UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    }

    //custom user details service that reads user information via a JPA repository
    @Bean
    public UserDetailsService userDetailsService (UserRepository userRepo){
        return username ->{
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    //builder to configure how security is handled at the web level
    //will only show /design and/orders pages only to authorized users
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/design", "/orders").access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                .requestMatchers("/", "/**").permitAll()
            )

            .formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/authenticate") // This tells Spring where to LISTEN for the form submission
                .usernameParameter("user") // Specifies the username field name
                .passwordParameter("pwd") // Specifies the password field name
                .defaultSuccessUrl("/design", true) // redirects user to design page if there are no recently saved accesed page
                .permitAll()
            );
        return http.build();
    }
}
