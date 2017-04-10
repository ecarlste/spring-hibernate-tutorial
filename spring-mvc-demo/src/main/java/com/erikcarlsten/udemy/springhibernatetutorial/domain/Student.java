package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private String country;
    private String programmingLanguage;

    @ElementCollection
    private List<String> operatingSystems;

}
