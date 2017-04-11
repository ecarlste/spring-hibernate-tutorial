package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.springframework.data.repository.Repository;

public interface StudentRepository extends Repository<Student, Long> {

    Student findByLastName(String lastName);

}
