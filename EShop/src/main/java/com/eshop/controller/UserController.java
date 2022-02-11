package com.eshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "user/register";
    }

    @RequestMapping("/shopping-cart")
    public String cart() {
        return "user/shopping-cart";
    }

    @RequestMapping("/order-history")
    public String order() {
        return "user/order-history";
    }
}
