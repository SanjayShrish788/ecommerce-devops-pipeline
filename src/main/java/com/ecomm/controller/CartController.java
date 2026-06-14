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

import java.math.BigDecimal;
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
        BigDecimal total = cartService.getTotalPrice(cartItems);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("cartTotal", total);
        return "customer/cart";
    }

    @GetMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId, Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName());
        Product product = productService.getProductById(productId);

        if (product != null && customer != null) {
            cartService.addToCart(customer, product);
        }
        return "redirect:/shop";
    }

    /**
     * Removes a specific cart item. Ownership is verified in the service layer
     * to prevent users from removing other customers' cart items.
     */
    @GetMapping("/remove/{cartId}")
    public String removeFromCart(@PathVariable Long cartId, Principal principal) {
        Customer customer = customerService.findByUsername(principal.getName());
        if (customer != null) {
            cartService.removeFromCart(cartId, customer);
        }
        return "redirect:/cart";
    }
}
