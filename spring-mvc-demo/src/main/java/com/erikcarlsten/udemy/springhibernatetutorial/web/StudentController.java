package com.erikcarlsten.udemy.springhibernatetutorial.web;

import com.erikcarlsten.udemy.springhibernatetutorial.domain.Student;
import com.erikcarlsten.udemy.springhibernatetutorial.util.CountryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping("/showForm")
    public String showForm(Model model) {
        Student student = new Student();

        model.addAttribute(student);
        model.addAttribute("countries", CountryUtil.getCountries());
        logger.info("Model attributes added to model: {}", model);

        return "views/student-form";
    }

    @RequestMapping("/processForm")
    public String processForm(@ModelAttribute("student") Student student) {
        logger.info("Student form submitted with the following data: {}", student);

        return "views/student-confirmation";
    }

}
