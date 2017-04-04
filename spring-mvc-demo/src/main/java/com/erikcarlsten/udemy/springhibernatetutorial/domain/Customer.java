package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class Customer {

    private String firstName;

    @NotNull(message = "is required")
    @Size(min=1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    @Min(value = 0, message = "Must be an integer value greater than or equal to 0")
    @Max(value = 10, message = "Must be an integer value less than or equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 letters/digits")
    private String postalCode;

}
