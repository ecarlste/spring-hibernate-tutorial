package com.erikcarlsten.udemy.springhibernatetutorial.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String showPage() {
        return "views/home";
    }

}
