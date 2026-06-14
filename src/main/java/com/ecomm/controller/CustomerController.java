package com.ecomm.controller;

import com.ecomm.model.Customer;
import com.ecomm.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
        // SECURITY FIX: Always force role to USER regardless of what was submitted.
        // Without this, a malicious user could POST role=ADMIN and gain admin access.
        customer.setRole("USER");
        try {
            customerService.registerCustomer(customer);
            return "redirect:/login?registered";
        } catch (Exception e) {
            model.addAttribute("error", "Username or email already exists");
            return "register";
        }
    }
}
