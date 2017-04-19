package com.erikcarlsten.udemy.springhibernatetutorial.web;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void listCustomersShouldReturnListCustomersTemplate() throws Exception {
        List<Customer> allCustomers = new ArrayList<>();
        allCustomers.add(new Customer("Erik", "Carlsten", "eshizzle@foo.bar"));
        allCustomers.add(new Customer("Adnyl", "Rebuts", "llfosho@bar.baz"));

        given(customerService.getAllCustomers()).willReturn(allCustomers);

        mvc.perform(get("/customer/list").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("views/list-customers"))
                .andExpect(MockMvcResultMatchers.view().name("views/list-customers"))
                .andExpect(model().attribute("customers", allCustomers));
    }

    @Test
    public void showFormForAddShouldReturnCustomerFormTemplate() throws Exception {
        mvc.perform(get("/customer/showFormForAdd").accept(MediaType.ALL))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/html;charset=UTF-8"))
                .andExpect(view().name("views/customer-form"))
                .andExpect(MockMvcResultMatchers.view().name("views/customer-form"))
                .andExpect(model().attributeExists("customer"));
    }

}
