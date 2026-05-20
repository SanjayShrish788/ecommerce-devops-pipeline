package com.ecomm.controller;

import com.ecomm.model.Cart;
import com.ecomm.model.Customer;
import com.ecomm.model.Product;
import com.ecomm.service.CartService;
import com.ecomm.service.CustomerService;
import com.ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductService productService;

    @GetMapping
    public String viewCart(Model model, Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName());
        List<Cart> cartItems = cartService.getCartItems(customer);
        model.addAttribute("cartItems", cartItems);
        return "customer/cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName());
        Product product = productService.getProductById(productId);
        
        if (product != null && customer != null) {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setProduct(product);
            cartService.addToCart(cart);
        }
        return "redirect:/shop";
    }
}
