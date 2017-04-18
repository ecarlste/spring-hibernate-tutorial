package com.erikcarlsten.udemy.springhibernatetutorial.service;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.domain.CustomerRepository;
import com.erikcarlsten.udemy.springhibernatetutorial.exception.CustomerNotSavedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;

public class RemoteCustomerServiceTest {

    @Rule
     public ExpectedException expectedException = ExpectedException.none();

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.customerService = new RemoteCustomerService(this.customerRepository);
    }

    @Test
    public void getAllCustomersShouldReturnAllCustomersAsList() {
        List<Customer> expectedCustomers = new ArrayList<>();
        expectedCustomers.add(new Customer("Erik", "Carlsten", "eshizzle@foo.bar"));
        expectedCustomers.add(new Customer("Lynda", "Stuber", "lmac@bar.wam"));

        given(this.customerRepository.findAllByOrderByLastName()).willReturn(expectedCustomers);

        List<Customer> actualCustomers = this.customerService.getAllCustomers();

        assertThat(expectedCustomers)
                .as("\nExpected Customers: %s,\nActual Customers: %s", expectedCustomers, actualCustomers)
                .isEqualTo(actualCustomers);
    }

    @Test
    public void getAllCustomersShouldReturnEmptyListWhenNoCustomers() throws Exception {
        List<Customer> emptyList = Collections.emptyList();

        given(customerRepository.findAllByOrderByLastName()).willReturn(emptyList);

        List<Customer> actualCustomers = customerService.getAllCustomers();

        assertThat(actualCustomers).isEmpty();
    }

    @Test
    public void saveCustomerShouldThrowExceptionWhenRepositorySaveReturnsNull() throws Exception {
        expectedException.expect(CustomerNotSavedException.class);
        expectedException.expectMessage("There was an error saving the Customer");

        given(customerRepository.save(any(Customer.class))).willReturn(null);

        Customer customerToSave = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");

        customerService.saveCustomer(customerToSave);
    }

    @Test
    public void saveCustomerShouldReturnCustomerWithIdWhenSaveSuccessful() throws Exception {
        Customer customerToSave = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");
        Customer customerWithId = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");
        customerWithId.setId(7L);

        given(customerRepository.save(customerToSave)).willReturn(customerWithId);

        Customer savedCustomer = customerService.saveCustomer(customerToSave);

        assertThat(savedCustomer).isEqualToComparingFieldByField(customerWithId);
    }

}