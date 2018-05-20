package ru.curriculum.service.curator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.curator.converter.DtoToCuratorConverter;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.curator.exception.CuratorNotFoundException;

import java.util.ArrayList;
import java.util.Collection;


@Component
public class CuratorCRUDService {
    @Autowired
    private CuratorRepository curatorRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private DtoToCuratorConverter dtoToCuratorConverter;

    public Collection<CuratorDto> findAllCurators() {
        Collection<CuratorDto> curatorDtos = new ArrayList<>();
        curatorRepository.findAll().forEach(curator ->
                curatorDtos.add(new CuratorDto(curator)));

        return curatorDtos;
    }

    public CuratorDto getCurator(Integer curatorId) {
        Curator curator = curatorRepository.findOne(curatorId);
        if(null == curator) {
            throw new CuratorNotFoundException(curatorId);
        }

        return new CuratorDto(curator);
    }

    public void create(CuratorDto dto) {
        Curator newCurator = dtoToCuratorConverter.convert(dto);
        curatorRepository.save(newCurator);
    }

    public void update(CuratorDto dto) {
        Curator curator = curatorRepository.findOne(dto.getId());
        if(null == curator) {
            throw new CuratorNotFoundException(dto.getId());
        }

        curator.surname(dto.getSurname());
        curator.firstName(dto.getFirstName());
        curator.patronymic(dto.getPatronymic());
        if(dto.getIsTeacher()) {
            curator.teacher(teacherRepository.findOne(dto.getId()));
        }
        if(null != dto.getPassword() && !dto.getPassword().isEmpty()) {
            curator.changePassword(dto.getPassword());
        }

        curatorRepository.save(curator);
    }

    public void delete(Integer curatorId) {
        Teacher teacher = teacherRepository.findByCuratorId(curatorId);
        if(null != teacher) {
            teacher.deleteCuratorProfile();
            teacherRepository.save(teacher);
        }
        curatorRepository.delete(curatorId);
    }
}
