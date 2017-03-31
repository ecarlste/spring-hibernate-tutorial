package com.erikcarlsten.udemy.springhibernatetutorial.web;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloWorldController {

    @GetMapping("/showForm")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "views/helloworld-form";
    }

    @PostMapping("/processForm")
    public String processForm(@ModelAttribute Student student) {
        return "views/helloworld";
    }

}
