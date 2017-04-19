package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {

    Student findByLastName(String lastName);

}
