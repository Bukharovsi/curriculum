package ru.curriculum.domain.admin.service.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.service.curator.dto.CuratorDTO;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;


public class CuratorDTOValidationTest extends Assert {
    private Validator validator;
    private ValidatorFactory validatorFactory;

    @Before
    public void setUp() {
        validatorFactory= Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void userDTOWithIdAndPasswordGraterThan3Character_validationSuccess() {
        CuratorDTO dto = new CuratorDTO();
        dto.setId(3);
        dto.setPassword("123");
        dto.setLogin("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithIdAndPasswordLessThan3Character_validationFailed() {
        CuratorDTO dto = new CuratorDTO();
        dto.setId(3);
        dto.setPassword("12");
        dto.setLogin("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWithIdAndNoPassword_validationFailed() {
        CuratorDTO dto = new CuratorDTO();
        dto.setLogin("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWIthIdAndPasswordIsEmptyString_validationSuccess() {
        CuratorDTO dto = new CuratorDTO();
        dto.setId(3);
        dto.setLogin("jhon");
        dto.setPassword("");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithoutIdAndPasswordGraterThan3_validationSuccess() {
        CuratorDTO dto = new CuratorDTO();
        dto.setPassword("123");
        dto.setLogin("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(0, violation.size());
    }

    @Test
    public void userDTOWithoutIdAndPasswordLessThan3_validationFailed() {
        CuratorDTO dto = new CuratorDTO();
        dto.setPassword("12");
        dto.setLogin("jhon");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWithUsernameLessThan3Character_validationFailed() {
        CuratorDTO dto = new CuratorDTO();
        dto.setPassword("123");
        dto.setLogin("j");
        dto.setFirstName("Mikola");
        dto.setSurname("Salo");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    @Test
    public void userDTOWithEmptyFirstNameAndSurname_validationFailed() {
        CuratorDTO dto = new CuratorDTO();
        dto.setPassword("123");
        dto.setLogin("jhon");
        dto.setFirstName("");
        dto.setSurname("");

        Set<ConstraintViolation<CuratorDTO>> violation = validator.validate(dto);

        assertEquals(2, violation.size());
    }
}
