package com.erikcarlsten.udemy.springhibernatetutorial.web;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

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
        return "views/list-customers";
    }

}
