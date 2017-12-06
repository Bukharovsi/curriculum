package ru.curriculum.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {
   public void initialize(PasswordConstraint constraint) {
   }

   public boolean isValid(String password, ConstraintValidatorContext context) {
      if(null != password && 2 > password.length()) {
         return false;
      }

      return true;
   }
}
