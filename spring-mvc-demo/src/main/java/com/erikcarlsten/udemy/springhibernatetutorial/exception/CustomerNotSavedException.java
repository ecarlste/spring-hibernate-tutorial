package com.erikcarlsten.udemy.springhibernatetutorial.exception;

public class CustomerNotSavedException extends RuntimeException {

    private static final long serialVersionUID = 4495916211361313284L;

    public CustomerNotSavedException(String message) {
        super(message);
    }

}
