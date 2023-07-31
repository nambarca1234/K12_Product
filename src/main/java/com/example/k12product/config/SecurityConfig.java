package com.example.k12product.config;

import com.example.k12product.service.Impl.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable().
                authorizeHttpRequests()
                .antMatchers("/index/**", "/register/**").permitAll()
                .antMatchers("/customer/**", "/admin/**").hasRole("ADMIN")
                .antMatchers("/product/**", "order/**").hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/index")
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/logout")
                .maximumSessions(1).expiredUrl("/login")
                .and()
                .and()
                .exceptionHandling().accessDeniedPage("/error")
        ;
        return http.build();
    }
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder builder) throws Exception{
        builder.userDetailsService(myUserDetailsService);
    }

}
