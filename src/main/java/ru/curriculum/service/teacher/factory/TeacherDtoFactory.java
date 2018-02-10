package ru.curriculum.service.teacher.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.service.teacher.dto.TeacherDto;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.curator.CuratorCRUDService;

@Component
public class TeacherDtoFactory {
    @Autowired
    private CuratorCRUDService curatorCRUDService;

    public TeacherDto createTeacherDTOBasedOnCurator(Integer curatorId) {
        CuratorDto curatorDto = curatorCRUDService.getCurator(curatorId);
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setCuratorId(curatorDto.getId());
        teacherDto.setCuratorLogin(curatorDto.getUsername());
        teacherDto.setSurname(curatorDto.getSurname());
        teacherDto.setFirstName(curatorDto.getFirstName());
        teacherDto.setPatronymic(curatorDto.getPatronymic());

        return teacherDto;
    }
}
