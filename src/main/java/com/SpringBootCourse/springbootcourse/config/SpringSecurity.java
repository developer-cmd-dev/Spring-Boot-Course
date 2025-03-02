package com.SpringBootCourse.springbootcourse.config;

import com.SpringBootCourse.springbootcourse.ExceptionHandler.BasicAuthenticationEntryPoint;
import com.SpringBootCourse.springbootcourse.service.CustomeUserDetailsIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurity   {

    @Autowired
    private CustomeUserDetailsIMPL userDetailsService;

    private final BasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

    public SpringSecurity(BasicAuthenticationEntryPoint basicAuthenticationEntryPoint) {
        this.basicAuthenticationEntryPoint = basicAuthenticationEntryPoint;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(request -> request
                        .requestMatchers("/public/**","/admin/add-admin").permitAll()
                        .requestMatchers("/journal/**", "/user/**").authenticated()
                        .requestMatchers("/admin/get-all-users").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(httpBasic->httpBasic.authenticationEntryPoint(this.basicAuthenticationEntryPoint))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
