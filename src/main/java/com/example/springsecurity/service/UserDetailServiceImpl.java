package com.example.springsecurity.service;

import com.example.springsecurity.dataprovider.models.Customer;
import com.example.springsecurity.dataprovider.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Log4j2
@AllArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Customer> firstByEmail = customerRepository.findFirstByEmail(email);
        if(firstByEmail.isPresent()){
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(firstByEmail.get().getAuthorities().get(0).getRole()));
            return new User(email, firstByEmail.get().getPassword(), authorities);
        }

        throw new UsernameNotFoundException("The username with the email was not found");
    }
}
