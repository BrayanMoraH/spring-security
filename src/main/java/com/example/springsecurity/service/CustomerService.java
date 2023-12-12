package com.example.springsecurity.service;

import com.example.springsecurity.dataprovider.models.Authority;
import com.example.springsecurity.dataprovider.models.Customer;
import com.example.springsecurity.dataprovider.repository.AuthorityRepository;
import com.example.springsecurity.dataprovider.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public Customer saveCustomer(Customer customer){
        List<Authority> authorities = new ArrayList<>();
        authorities.add(findAuthorityByName());
        String encodePassword = passwordEncoder.encode(customer.getPassword());
        customer.setPassword(encodePassword);
        customer.setAuthorities(authorities);
        customer.setCustomerId(UUID.randomUUID().toString());
        return customerRepository.save(customer);
    }

    private Authority findAuthorityByName(){
        Authority authority = authorityRepository.findById("8A9F2DD9-8EE4-4824-8549-FB27C6FF0243").get();
        return authority;
    }
}
