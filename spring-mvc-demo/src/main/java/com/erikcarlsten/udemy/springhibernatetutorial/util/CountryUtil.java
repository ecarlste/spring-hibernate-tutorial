package com.erikcarlsten.udemy.springhibernatetutorial.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CountryUtil {

    private static final Map<String, String> countries = new LinkedHashMap<>();

    static {
        countries.put("BR", "Brazil");
        countries.put("FR", "France");
        countries.put("DE", "Germany");
        countries.put("IN", "India");
    }

    public static Collection<String> getCountries() {
        return countries.values();
    }

}
