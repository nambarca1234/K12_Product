package com.example.k12product.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().
                authorizeHttpRequests().antMatchers("/index/**","/register/**").permitAll()
                .antMatchers("/customer/**","/admin").hasAnyRole("ADMIN")
                .antMatchers("/product/**","/order/**").hasAnyRole("ADMIN","USER")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/index")
                .and()
                .exceptionHandling().accessDeniedPage("/error")
                ;
        return http.build();
    }
    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("admin")).roles("ADMIN");
        builder.inMemoryAuthentication()
                .withUser("nam").password(passwordEncoder.encode("123")).roles("USER");
    }

}
