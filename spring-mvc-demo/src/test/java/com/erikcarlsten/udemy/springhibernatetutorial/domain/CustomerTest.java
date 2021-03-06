package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTest {

    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL = "fake-email@foo.bar";
    private static final String FIRST_NAME_EMPTY_MESSAGE = "First name must not be empty";
    private static final String LAST_NAME_EMPTY_MESSAGE = "Last name must not be empty";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createShouldThrowExceptionWhenFirstNameIsNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(FIRST_NAME_EMPTY_MESSAGE);
        new Customer(null, LAST_NAME, EMAIL);
    }

    @Test
    public void createShouldThrowExceptionWhenFirstNameIsEmpty() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(FIRST_NAME_EMPTY_MESSAGE);
        new Customer("", LAST_NAME, EMAIL);
    }

    @Test
    public void createShouldThrowExceptionWhenLastNameIsNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(LAST_NAME_EMPTY_MESSAGE);
        new Customer(FIRST_NAME, null, EMAIL);
    }

    @Test
    public void createShouldThrowExceptionWhenLastNameIsEmpty() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(LAST_NAME_EMPTY_MESSAGE);
        new Customer(FIRST_NAME, "", EMAIL);
    }

    @Test
    public void saveShouldPersistData() throws Exception {
        Customer customer = entityManager.persistFlushFind(new Customer(FIRST_NAME, LAST_NAME, EMAIL));
        assertThat(customer.getId()).isNotNull();
        assertThat(customer.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(customer.getLastName()).isEqualTo(LAST_NAME);
        assertThat(customer.getEmail()).isEqualTo(EMAIL);
    }

}
