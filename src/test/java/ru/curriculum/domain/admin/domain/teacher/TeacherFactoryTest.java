package ru.curriculum.domain.admin.domain.teacher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.directories.academicDegree.AcademicDegreeRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.service.teacher.dto.TeacherDto;
import ru.curriculum.domain.teacher.factory.TeacherFactory;

import javax.persistence.EntityNotFoundException;


@RunWith(MockitoJUnitRunner.class)
public class TeacherFactoryTest extends Assert {
    @Mock
    private AcademicDegreeRepository academicDegreeRepository;
    @Mock
    private CuratorRepository curatorRepository;
    @InjectMocks
    private TeacherFactory teacherFactory;

    @Before
    public void setUp() {
        Mockito
                .when(academicDegreeRepository.findOne("ph_d"))
                .thenReturn(new AcademicDegree("ph_d", "Доктор наук"));
        Mockito
                .when(academicDegreeRepository.findOne("none"))
                .thenReturn(null);
        Mockito
                .when(curatorRepository.findOne(1))
                .thenReturn(new Curator());
    }

    @Test
    public void createTeacher() {
        TeacherDto dto = getTeacherDTO();
        Teacher teacher = teacherFactory.create(dto);

        assertEquals(dto.getSurname(), teacher.surname());
        assertEquals(dto.getFirstName(), teacher.firstName());
        assertEquals(dto.getPatronymic(), teacher.patronymic());
        assertEquals(dto.getAcademicDegreeCode(), teacher.academicDegree().code());
        assertEquals(dto.getAcademicDegreeName(), teacher.academicDegree().name());
        assertEquals(dto.getPlaceOfWork(), teacher.placeOfWork());
        assertEquals(dto.getPositionHeld(), teacher.positionHeld());
    }

    @Test(expected = EntityNotFoundException.class)
    public void createTeacherFromDTOWhichContainNoneExistenceAcademicDegreeCode_mustBeException() {
        TeacherDto dto = getTeacherDTO();
        dto.setAcademicDegreeCode("none");
        teacherFactory.create(dto);
    }

    @Test(expected = NullPointerException.class)
    public void createTeacherFromDTOWhichNotContainSurname_mustBeException() {
        TeacherDto dto = getTeacherDTO();
        dto.setSurname(null);
        teacherFactory.create(dto);
    }

    @Test
    public void createUserFromUserDTOWhereDefineUserId_mustBeCreateTeacherWithUserAccount() {
        TeacherDto dto = getTeacherDTO();
        dto.setCuratorId(1);
        Teacher teacher = teacherFactory.create(dto);

        assertTrue(teacher.hasCuratorProfile());
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
