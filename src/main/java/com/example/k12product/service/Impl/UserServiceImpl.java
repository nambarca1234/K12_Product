package com.example.k12product.service.Impl;

import com.example.k12product.model.Role;
import com.example.k12product.model.User;
import com.example.k12product.repository.RoleRepository;
import com.example.k12product.repository.UserRepository;
import com.example.k12product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    @Override
    public void create(User user, int[] roleIds) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<Role> roles = new ArrayList<>();
        if (roleIds.length>0){
            for (int i = 0; i < roleIds.length; i++) {
                Role role = roleRepository.findById(roleIds[i]).get();
                role.getUsers().add(user);
                roles.add(role);
            }
        }

        user.setRoles(roles);
        userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
