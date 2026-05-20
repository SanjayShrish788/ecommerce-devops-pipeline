package com.ecomm.service;

import com.ecomm.model.Customer;
import com.ecomm.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Customer registerCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        if (customer.getRole() == null || customer.getRole().isEmpty()) {
            customer.setRole("USER");
        }
        return customerRepository.save(customer);
    }

    public Customer findByUsername(String username) {
        return customerRepository.findByUsername(username).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
