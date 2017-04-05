package com.erikcarlsten.udemy.springhibernatetutorial.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    public void initialize(CourseCode courseCode) {
        coursePrefix = courseCode.value();
    }

    public boolean isValid(String code, ConstraintValidatorContext context) {
        return code == null || code.startsWith(coursePrefix);
    }

}
