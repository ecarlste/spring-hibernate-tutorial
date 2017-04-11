package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByLastName(String lastName);
}
