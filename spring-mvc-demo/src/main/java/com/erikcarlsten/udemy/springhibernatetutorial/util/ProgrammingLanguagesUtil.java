package com.erikcarlsten.udemy.springhibernatetutorial.util;

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

    public static List<String> getNames() {
        return names;
    }

}
