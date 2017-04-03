package com.erikcarlsten.udemy.springhibernatetutorial.web;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hello")
public class HelloWorldController {

    private Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        logger.info("Showing hello form, adding new student attribute to model: {}", model);

        return "views/helloworld-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute Student student) {
        logger.info("Processing helloworld form submission for student{}", student);

        return "views/helloworld";
    }

}
