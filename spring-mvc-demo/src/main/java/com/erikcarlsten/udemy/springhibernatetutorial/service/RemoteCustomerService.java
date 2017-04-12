package com.erikcarlsten.udemy.springhibernatetutorial.service;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.domain.CustomerRepository;
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

    private RemoteCustomerService() {
    }

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
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public Customer getCustomer(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.delete(id);
    }

}
