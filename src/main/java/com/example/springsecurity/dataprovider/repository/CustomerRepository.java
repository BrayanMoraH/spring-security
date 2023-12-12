package com.example.springsecurity.dataprovider.repository;

import com.example.springsecurity.dataprovider.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {

    Optional<Customer> findFirstByEmail(String email);
}
