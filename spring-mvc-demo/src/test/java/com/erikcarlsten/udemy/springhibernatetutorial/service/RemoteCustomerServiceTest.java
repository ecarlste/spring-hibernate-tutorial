package com.erikcarlsten.udemy.springhibernatetutorial.service;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.domain.CustomerRepository;
import com.erikcarlsten.udemy.springhibernatetutorial.exception.CustomerNotFoundException;
import com.erikcarlsten.udemy.springhibernatetutorial.exception.CustomerNotSavedException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.doThrow;

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
        Customer expectedCustomer = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");
        expectedCustomer.setId(7L);

        Customer customerSaved = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");
        customerSaved.setId(7L);
        given(customerRepository.save(customerToSave)).willReturn(customerSaved);

        Customer actualCustomer = customerService.saveCustomer(customerToSave);

        assertThat(actualCustomer).isEqualToComparingFieldByField(expectedCustomer);
    }

    @Test
    public void getCustomerShouldThrowExceptionWhenRepositoryFindOneReturnsNull() throws Exception {
        Long customerId = 7L;
        expectedException.expect(CustomerNotFoundException.class);
        expectedException.expectMessage("Customer with id: " + customerId + " not found");

        given(customerRepository.findOne(customerId)).willReturn(null);

        customerService.getCustomer(customerId);
    }

    @Test
    public void getCustomerShouldReturnCustomerWithCorrectIdWhenCustomerFound() throws Exception {
        Long customerId = 7L;
        Customer expectedCustomer = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");
        expectedCustomer.setId(customerId);

        Customer customerFound = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");
        customerFound.setId(customerId);
        given(customerRepository.findOne(customerId)).willReturn(customerFound);

        Customer actualCustomer = customerService.getCustomer(customerId);

        assertThat(actualCustomer).isEqualToComparingFieldByField(expectedCustomer);
    }

    @Test
    public void deleteCustomerShouldRethrowIllegalArgumentExceptionWhenIdNull() {
        expectedException.expect(IllegalArgumentException.class);
        doThrow(new IllegalArgumentException()).when(customerRepository).delete(anyLong());

        customerService.deleteCustomer(1L);
    }

    @Test
    public void deleteCustomerShouldRethrowEmptyResultDataAccessExceptionWhenCustomerNotFound() {
        expectedException.expect(EmptyResultDataAccessException.class);
        doThrow(new EmptyResultDataAccessException(1)).when(customerRepository).delete(anyLong());

        customerService.deleteCustomer(1L);
    }

}