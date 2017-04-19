package com.erikcarlsten.udemy.springhibernatetutorial.web;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    public void saveCustomerShouldSaveCustomerAndRedirectToCustomerListTemplate() throws Exception {
        Customer customer = new Customer("Erik", "Carlsten", "eshizzle@foo.bar");

        given(customerService.saveCustomer(customer)).willReturn(customer);

        String content = objectMapper.writeValueAsString(customer);
        MockHttpServletRequestBuilder requestBuilder = post("/customer/saveCustomer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        mvc.perform(requestBuilder)
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customer/list"))
                .andExpect(redirectedUrl("/customer/list"));
    }

}
