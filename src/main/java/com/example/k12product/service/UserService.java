package com.example.k12product.service;

import com.example.k12product.model.User;

public interface UserService {
    void create (User user);
    void create (User user, int [] roleIds);
    User findByUsername(String username);
}
