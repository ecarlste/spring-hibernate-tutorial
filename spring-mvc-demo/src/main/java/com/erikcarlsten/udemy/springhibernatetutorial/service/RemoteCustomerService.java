package com.erikcarlsten.udemy.springhibernatetutorial.service;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.domain.CustomerRepository;
import com.erikcarlsten.udemy.springhibernatetutorial.exception.CustomerNotFoundException;
import com.erikcarlsten.udemy.springhibernatetutorial.exception.CustomerNotSavedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RemoteCustomerService implements CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(RemoteCustomerService.class);

    private CustomerRepository customerRepository;

    @Autowired
    public RemoteCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        List<Customer> sorted = customerRepository.findAllByOrderByLastName();

        logger.info("sorted: {}", sorted);

        return sorted;
    }

    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);

        if (savedCustomer == null) {
            logger.error("Repository returned null Customer");
            throw new CustomerNotSavedException("There was an error saving the Customer");
        }

        return savedCustomer;
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        Customer customer = customerRepository.findOne(id);

        if (customer == null) {
            logger.info("Customer with id: {} not found", id);
            throw new CustomerNotFoundException("Customer with id: " + id + " not found");
        }

        return customer;
    }

    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }

}
