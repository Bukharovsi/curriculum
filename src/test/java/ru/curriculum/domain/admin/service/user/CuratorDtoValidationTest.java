package ru.curriculum.domain.admin.service.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.service.curator.dto.CuratorDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class CuratorDtoValidationTest extends Assert {
    private Validator validator;
    private ValidatorFactory validatorFactory;

    @Before
    public void setUp() {
        validatorFactory= Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void userDTOWithIdAndPasswordGraterThan3Character_validationSuccess() {
        CuratorDto dto = new CuratorDto();
        dto.setId(3);
        dto.setPassword("123");
        dto.setUsername("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithIdAndPasswordLessThan3Character_validationFailed() {
        CuratorDto dto = new CuratorDto();
        dto.setId(3);
        dto.setPassword("12");
        dto.setUsername("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWithIdAndNoPassword_validationFailed() {
        CuratorDto dto = new CuratorDto();
        dto.setUsername("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWIthIdAndPasswordIsEmptyString_validationSuccess() {
        CuratorDto dto = new CuratorDto();
        dto.setId(3);
        dto.setUsername("jhon");
        dto.setPassword("");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithoutIdAndPasswordGraterThan3_validationSuccess() {
        CuratorDto dto = new CuratorDto();
        dto.setPassword("123");
        dto.setUsername("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithoutIdAndPasswordLessThan3_validationFailed() {
        CuratorDto dto = new CuratorDto();
        dto.setPassword("12");
        dto.setUsername("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWithUsernameLessThan3Character_validationFailed() {
        CuratorDto dto = new CuratorDto();
        dto.setPassword("123");
        dto.setUsername("j");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWithEmptyFirstNameAndSurname_validationFailed() {
        CuratorDto dto = new CuratorDto();
        dto.setPassword("123");
        dto.setUsername("jhon");
        dto.setFirstName("");
        dto.setSurname("");

        Set<ConstraintViolation<CuratorDto>> violation = validator.validate(dto);

        assertEquals(2, violation.size());
    }
}
