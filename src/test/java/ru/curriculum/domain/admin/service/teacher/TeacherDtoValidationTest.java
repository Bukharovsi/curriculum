package ru.curriculum.domain.admin.service.teacher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.service.teacher.dto.TeacherDto;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class TeacherDtoValidationTest extends Assert {
    private Validator validator;
    private ValidatorFactory validatorFactory;

    @Before
    public void setUp() {
        validatorFactory= Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void teacherDTOWithAllValidField_validationSuccess() {
        TeacherDto dto = getTeacherDTO();

        Set<ConstraintViolation<TeacherDto>> violation = validator.validate(dto);

        assertEquals(0, violation.size());
    }

    @Test
    public void teacherDTOWithEmptyFullName_validationFailed() {
        TeacherDto dto = getTeacherDTO();
        dto.setSurname(null);
        dto.setFirstName("");
        dto.setPatronymic("");

        Set<ConstraintViolation<TeacherDto>> violation = validator.validate(dto);

        assertEquals(3, violation.size());
    }

    @Test
    public void teacherDTOWithEmptyAcademicDegreeCode_validationFailed() {
        TeacherDto dto = getTeacherDTO();
        dto.setAcademicDegreeCode(null);

        Set<ConstraintViolation<TeacherDto>> violation = validator.validate(dto);

        assertEquals(1, violation.size());
    }

    public TeacherDto getTeacherDTO() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1);
        teacherDto.setSurname("Иванов");
        teacherDto.setFirstName("Иван");
        teacherDto.setPatronymic("Иванович");
        teacherDto.setAcademicDegreeCode("ph_d");
        teacherDto.setAcademicDegreeName("Доктор наук");
        teacherDto.setPlaceOfWork("ИРОРТ");

        return teacherDto;
    }
}
