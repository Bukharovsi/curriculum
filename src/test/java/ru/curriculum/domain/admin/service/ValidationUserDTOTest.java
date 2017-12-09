package ru.curriculum.domain.admin.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.service.UserDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class ValidationUserDTOTest {
    private Validator validator;
    private ValidatorFactory validatorFactory;

    @Before
    public void setUp() {
        validatorFactory= Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    @Test
    public void userDTOWithIdAndWithPasswordGraterThan3Character_validationSuccess() {
        UserDTO dto = new UserDTO();
        dto.setId(3);
        dto.setPassword("123");
        dto.setUsername("jhon");
        dto.setFirstname("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<UserDTO>> violation = validator.validate(dto);

        Assert.assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithIdAndPasswordLessThan3Character_validationSuccess() {
        UserDTO dto = new UserDTO();
        dto.setId(3);
        dto.setPassword("12");
        dto.setUsername("jhon");
        dto.setFirstname("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<UserDTO>> violation = validator.validate(dto);

        Assert.assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithoutIdAndPasswordGraterThan3_validationSuccess() {
        UserDTO dto = new UserDTO();
        dto.setPassword("123");
        dto.setUsername("jhon");
        dto.setFirstname("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<UserDTO>> violation = validator.validate(dto);

        Assert.assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithoutIdAndPasswordLessThan3_validationFailed() {
        UserDTO dto = new UserDTO();
        dto.setPassword("12");
        dto.setUsername("jhon");
        dto.setFirstname("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<UserDTO>> violation = validator.validate(dto);

        Assert.assertEquals(1, violation.size());
    }
}
