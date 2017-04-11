package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    Customer findByLastName(String lastName);

    List<Customer> findAllByOrderByLastName();

}
