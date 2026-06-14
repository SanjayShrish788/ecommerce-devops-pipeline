package com.ecomm.controller;

import com.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/shop")
    public String shop(Model model, Principal principal) {
        model.addAttribute("products", productService.getAllProducts());
        if (principal != null) {
            model.addAttribute("username", principal.getName());
        } else {
            model.addAttribute("username", "Guest");
        }
        return "customer/shop";
    }
}
