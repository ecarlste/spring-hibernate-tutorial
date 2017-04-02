package com.erikcarlsten.udemy.springhibernatetutorial.util;

import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;

public class ProgrammingLanguagesUtil {

    private static final List<String> names = new ArrayList<>();

    static {
        names.add("Java");
        names.add("C#");
        names.add("PHP");
        names.add("Ruby");
    }

    @ModelAttribute("programmingLanguages")
    public static List<String> getNames() {
        return names;
    }

}
