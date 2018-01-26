package ru.curriculum.service.teacher.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.service.teacher.dto.TeacherDTO;
import ru.curriculum.service.curator.dto.CuratorDTO;
import ru.curriculum.service.curator.CuratorCRUDService;

@Component
public class TeacherDTOFactory {
    @Autowired
    private CuratorCRUDService curatorCRUDService;

    public TeacherDTO createTeacherDTOBasedOnCurator(Integer curatorId) {
        CuratorDTO curatorDTO = curatorCRUDService.getCurator(curatorId);
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setCuratorId(curatorDTO.getId());
        teacherDTO.setCuratorLogin(curatorDTO.getUsername());
        teacherDTO.setSurname(curatorDTO.getSurname());
        teacherDTO.setFirstName(curatorDTO.getFirstName());
        teacherDTO.setPatronymic(curatorDTO.getPatronymic());

        return teacherDTO;
    }
}
