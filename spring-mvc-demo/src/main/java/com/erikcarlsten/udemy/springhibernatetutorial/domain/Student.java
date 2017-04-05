package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String firstName;
    private String lastName;
    private String country;
    private String programmingLanguage;
    private List<String> operatingSystems;

}
