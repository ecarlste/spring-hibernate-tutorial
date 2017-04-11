package com.erikcarlsten.udemy.springhibernatetutorial.service;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.domain.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.customerService = new CustomerService(this.customerRepository);
    }

    @Test
    public void getAllCustomersShouldReturnAllCustomersAsList() {
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer("Erik", "Carlsten"));
        expectedCustomers.add(new Customer("Lynda", "Stuber"));

        given(this.customerRepository.findAll()).willReturn(expectedCustomers);

        List<Customer> actualCustomers = this.customerService.getAllCustomers();

        assertThat(expectedCustomers)
                .as("\nExpected Customers: %s,\nActual Customers: %s", expectedCustomers, actualCustomers)
                .isEqualTo(actualCustomers);
    }

}