package ru.curriculum.service.validation;

import ru.curriculum.service.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, UserDTO> {
   public void initialize(PasswordConstraint constraint) {
   }

   public boolean isValid(UserDTO userDTO, ConstraintValidatorContext context) {
       if(null == userDTO.getId() && (null == userDTO.getPassword() ||  3 > userDTO.getPassword().length())) {
           return false;
       }

      return true;
   }
}
