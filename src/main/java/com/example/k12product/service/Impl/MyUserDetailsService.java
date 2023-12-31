package com.example.k12product.service.Impl;

import com.example.k12product.model.User;
import com.example.k12product.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user!=  null){
            return new  org.springframework.security.core.userdetails.User(
                    user.getUsername(),user.getPassword(),user.getRoles()
                    .stream().map((s)->new SimpleGrantedAuthority(s.getName())).collect(Collectors.toList())
            );

        }else {
            throw new UsernameNotFoundException("Tài Khoản Không tồn tại");
        }
    }
}
