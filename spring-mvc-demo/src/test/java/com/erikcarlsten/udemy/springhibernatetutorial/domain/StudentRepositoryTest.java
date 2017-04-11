package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void findByLastNameShouldReturnStudent() throws Exception {
        this.entityManager.persist(new Student("Erik", "Carlsten"));
        Student student = this.studentRepository.findByLastName("Carlsten");
        assertThat(student.getFirstName()).isEqualTo("Erik");
        assertThat(student.getLastName()).isEqualTo("Carlsten");
    }

    @Test
    public void findByLastNameShouldReturnNullWhenNoStudent() throws Exception {
        this.entityManager.persist(new Student("Erik", "Carlsten"));
        Student student = this.studentRepository.findByLastName("Stuber");
        assertThat(student).isNull();
    }

}