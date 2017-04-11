package com.erikcarlsten.udemy.springhibernatetutorial.service;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.domain.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@Service
public class RemoteCustomerService implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

}
