package ru.curriculum.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.service.user.UserDTO;
import ru.curriculum.service.user.UserCRUDService;

@Component
public class TeacherDTOFactory {
    @Autowired
    private UserCRUDService userCRUDService;

    public TeacherDTO createTeacherDTOBasedOnUser(Integer userId) {
        UserDTO userDTO = userCRUDService.getUser(userId);
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setUserId(userDTO.getId());
        teacherDTO.setUsername(userDTO.getUsername());
        teacherDTO.setSurname(userDTO.getSurname());
        teacherDTO.setFirstname(userDTO.getFirstname());
        teacherDTO.setLastname(userDTO.getLastname());

        return teacherDTO;
    }
}
