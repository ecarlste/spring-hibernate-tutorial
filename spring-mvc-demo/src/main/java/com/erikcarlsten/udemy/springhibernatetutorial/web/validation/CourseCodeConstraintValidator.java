package com.erikcarlsten.udemy.springhibernatetutorial.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
   public void initialize(CourseCode constraint) {
   }

   public boolean isValid(String obj, ConstraintValidatorContext context) {
      return false;
   }
}
