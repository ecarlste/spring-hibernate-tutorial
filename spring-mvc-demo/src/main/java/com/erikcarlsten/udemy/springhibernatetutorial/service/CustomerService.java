package com.erikcarlsten.udemy.springhibernatetutorial.service;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
}
