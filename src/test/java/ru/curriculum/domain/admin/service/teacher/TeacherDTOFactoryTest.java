package ru.curriculum.domain.admin.service.teacher;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ru.curriculum.service.user.UserCRUDService;
import ru.curriculum.service.user.UserDTO;
import ru.curriculum.service.teacher.TeacherDTO;
import ru.curriculum.service.teacher.TeacherDTOFactory;

@RunWith(MockitoJUnitRunner.class)
public class TeacherDTOFactoryTest extends Assert {
    @Mock
    private UserCRUDService userCRUDService;
    @InjectMocks
    private TeacherDTOFactory teacherDTOFactory;
    private UserDTO userDTO;

    @Before
    public void setUp() {
        userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setUsername("username");
        userDTO.setSurname("Test");
        userDTO.setFirstname("Test");
        userDTO.setLastname("Test");
        userDTO.setPassword("123");
        Mockito
                .when(userCRUDService.getUser(1))
                .thenReturn(userDTO);
    }

    @Test
    public void createTeacherDTOBasedOnUser_mustBeCreateCorrectly() {
        TeacherDTO teacherDTO = teacherDTOFactory.createTeacherDTOBasedOnUser(1);

        assertEquals(userDTO.getFirstname(), teacherDTO.getFirstname());
        assertEquals(userDTO.getSurname(), teacherDTO.getSurname());
        assertEquals(userDTO.getLastname(), teacherDTO.getLastname());
        assertEquals(userDTO.getId(), teacherDTO.getUserId());
        assertEquals(userDTO.getUsername(), teacherDTO.getUsername());
    }
}
