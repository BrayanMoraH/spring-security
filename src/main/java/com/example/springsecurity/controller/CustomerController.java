package com.example.springsecurity.controller;

import com.example.springsecurity.dataprovider.models.Customer;
import com.example.springsecurity.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/sign-up")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){
        Customer body = customerService.saveCustomer(customer);
        return ResponseEntity.created(URI.create("/get-customers")).body(body);
    }

}
