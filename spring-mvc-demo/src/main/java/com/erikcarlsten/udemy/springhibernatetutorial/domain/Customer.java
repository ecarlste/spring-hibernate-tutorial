package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    public Customer(String firstName, String lastName, String email) {
        Assert.hasLength(firstName, "First name must not be empty");
        Assert.hasLength(lastName, "Last name must not be empty");

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
