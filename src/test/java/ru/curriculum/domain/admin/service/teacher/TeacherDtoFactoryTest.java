package ru.curriculum.domain.admin.service.teacher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.curriculum.service.curator.CuratorCRUDService;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.teacher.dto.TeacherDto;
import ru.curriculum.service.teacher.factory.TeacherDtoFactory;

@RunWith(MockitoJUnitRunner.class)
public class TeacherDtoFactoryTest extends Assert {
    @Mock
    private CuratorCRUDService curatorCRUDService;
    @InjectMocks
    private TeacherDtoFactory teacherDtoFactory;
    private CuratorDto curatorDto;

    @Before
    public void setUp() {
        curatorDto = new CuratorDto();
        curatorDto.setId(1);
        curatorDto.setUsername("username");
        curatorDto.setSurname("Test");
        curatorDto.setFirstName("Test");
        curatorDto.setPatronymic("Test");
        curatorDto.setPassword("123");
        Mockito
                .when(curatorCRUDService.getCurator(1))
                .thenReturn(curatorDto);
    }

    @Test
    public void createTeacherDTOBasedOnUser_mustBeCreateCorrectly() {
        TeacherDto teacherDto = teacherDtoFactory.createTeacherDTOBasedOnCurator(1);

        assertEquals(curatorDto.getFirstName(), teacherDto.getFirstName());
        assertEquals(curatorDto.getSurname(), teacherDto.getSurname());
        assertEquals(curatorDto.getPatronymic(), teacherDto.getPatronymic());
        assertEquals(curatorDto.getId(), teacherDto.getCuratorId());
        assertEquals(curatorDto.getUsername(), teacherDto.getCuratorLogin());
    }
}
