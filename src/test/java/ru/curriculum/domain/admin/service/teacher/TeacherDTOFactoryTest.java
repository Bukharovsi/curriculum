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
import ru.curriculum.service.curator.dto.CuratorDTO;
import ru.curriculum.service.teacher.dto.TeacherDTO;
import ru.curriculum.service.teacher.factory.TeacherDTOFactory;

@RunWith(MockitoJUnitRunner.class)
public class TeacherDTOFactoryTest extends Assert {
    @Mock
    private CuratorCRUDService curatorCRUDService;
    @InjectMocks
    private TeacherDTOFactory teacherDTOFactory;
    private CuratorDTO curatorDTO;

    @Before
    public void setUp() {
        curatorDTO = new CuratorDTO();
        curatorDTO.setId(1);
        curatorDTO.setLogin("username");
        curatorDTO.setSurname("Test");
        curatorDTO.setFirstName("Test");
        curatorDTO.setPatronymic("Test");
        curatorDTO.setPassword("123");
        Mockito
                .when(curatorCRUDService.getCurator(1))
                .thenReturn(curatorDTO);
    }

    @Test
    public void createTeacherDTOBasedOnUser_mustBeCreateCorrectly() {
        TeacherDTO teacherDTO = teacherDTOFactory.createTeacherDTOBasedOnCurator(1);

        assertEquals(curatorDTO.getFirstName(), teacherDTO.getFirstName());
        assertEquals(curatorDTO.getSurname(), teacherDTO.getSurname());
        assertEquals(curatorDTO.getPatronymic(), teacherDTO.getPatronymic());
        assertEquals(curatorDTO.getId(), teacherDTO.getCuratorId());
        assertEquals(curatorDTO.getLogin(), teacherDTO.getCuratorLogin());
    }
}
