package com.ecomm.service;

import com.ecomm.model.Cart;
import com.ecomm.model.Customer;
import com.ecomm.model.Product;
import com.ecomm.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getCartItems(Customer customer) {
        return cartRepository.findByCustomer(customer);
    }

    /**
     * Adds a product to the cart. If the product already exists in the cart,
     * the quantity is incremented instead of inserting a duplicate row.
     */
    public void addToCart(Customer customer, Product product) {
        Optional<Cart> existing = cartRepository.findByCustomerAndProduct(customer, product);
        if (existing.isPresent()) {
            Cart cart = existing.get();
            cart.setQuantity(cart.getQuantity() + 1);
            cartRepository.save(cart);
        } else {
            Cart cart = new Cart();
            cart.setCustomer(customer);
            cart.setProduct(product);
            cart.setQuantity(1);
            cartRepository.save(cart);
        }
    }

    /**
     * Removes a cart item by its ID, after verifying it belongs to the customer.
     */
    public void removeFromCart(Long cartId, Customer customer) {
        cartRepository.findById(cartId).ifPresent(cart -> {
            if (cart.getCustomer().getId().equals(customer.getId())) {
                cartRepository.delete(cart);
            }
        });
    }

    /**
     * Calculates the total price of all items in the cart.
     */
    public BigDecimal getTotalPrice(List<Cart> cartItems) {
        return cartItems.stream()
                .map(item -> item.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
