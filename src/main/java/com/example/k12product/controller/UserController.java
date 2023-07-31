package com.example.k12product.controller;

import com.example.k12product.model.User;
import com.example.k12product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/index")
    private String index(Model model){
        return "/index";
    }

    @GetMapping("/register")
    private String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/user/register";
    }

    @PostMapping("/user/save")
    private String save(@ModelAttribute User user, @RequestParam int[] role){
        userService.create(user,role);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/product")
    public String product() {
        return "product/product";
    }
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
