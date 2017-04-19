package com.erikcarlsten.udemy.springhibernatetutorial.exception;

public class CustomerNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 8374540472562430287L;

    public CustomerNotFoundException(String message) {
        super(message);
    }

}
