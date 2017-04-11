package com.erikcarlsten.udemy.springhibernatetutorial.web;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import com.erikcarlsten.udemy.springhibernatetutorial.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("/showForm")
    public String showForm(Model model) {
        Customer customer = new Customer();

        model.addAttribute(customer);

        return "views/customer-form";
    }

    @PostMapping("/processForm")
    public String checkCustomerInfo(@Valid @ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        logger.info("Customer form submitted with the following data: {}", customer);

        if (bindingResult.hasErrors()) {
            return "views/customer-form";
        }

        return "views/customer-confirmation";
    }

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();

        logger.info("Retrieved all customers: {}", customers);

        model.addAttribute("customers", customers);

        logger.info("Added customers to model: {}", model);

        return "views/list-customers";
    }

}
