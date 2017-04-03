package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import lombok.*;

import java.util.List;

@Data
public class Student {

    private String firstName;
    private String lastName;
    private String country;
    private String programmingLanguage;
    private List<String> operatingSystems;

}
