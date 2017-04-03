package com.erikcarlsten.udemy.springhibernatetutorial.util;

import java.util.ArrayList;
import java.util.List;

public class OperatingSystemUtil {

    private static final List<String> names = new ArrayList<>();

    static {
        names.add("Linux");
        names.add("Mac OS");
        names.add("Windows");
    }

    public static List<String> getNames() {
        return names;
    }

}
