package com.erikcarlsten.udemy.springhibernatetutorial.domain;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class StudentTest {

    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String FIRST_NAME_EMPTY_MESSAGE = "First name must not be empty";
    private static final String LAST_NAME_EMPTY_MESSAGE = "Last name must not be empty";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void createShouldThrowExceptionWhenFirstNameIsNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(FIRST_NAME_EMPTY_MESSAGE);
        new Student(null, LAST_NAME);
    }

    @Test
    public void createShouldThrowExceptionWhenFirstNameIsEmpty() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(FIRST_NAME_EMPTY_MESSAGE);
        new Student("", LAST_NAME);
    }

    @Test
    public void createShouldThrowExceptionWhenLastNameIsNull() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(LAST_NAME_EMPTY_MESSAGE);
        new Student(FIRST_NAME, null);
    }

    @Test
    public void createShouldThrowExceptionWhenLastNameIsEmpty() throws Exception {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(LAST_NAME_EMPTY_MESSAGE);
        new Student(FIRST_NAME, "");
    }

}
