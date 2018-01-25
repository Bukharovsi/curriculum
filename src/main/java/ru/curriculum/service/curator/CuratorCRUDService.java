package ru.curriculum.service.curator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.curator.converter.DtoToCuratorConverter;
import ru.curriculum.service.curator.dto.CuratorDTO;
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

    public Collection<CuratorDTO> findAllCurators() {
        Collection<CuratorDTO> curatorDTOs = new ArrayList<>();
        curatorRepository.findAll().forEach(curator ->
                curatorDTOs.add(new CuratorDTO(curator)));

        return curatorDTOs;
    }

    public CuratorDTO getCurator(Integer curatorId) {
        Curator curator = curatorRepository.findOne(curatorId);
        if(null == curator) {
            throw new CuratorNotFoundException(curatorId);
        }

        return new CuratorDTO(curator);
    }

    public void create(CuratorDTO dto) {
        Curator newCurator = dtoToCuratorConverter.convert(dto);
        curatorRepository.save(newCurator);
    }

    public void update(CuratorDTO dto) {
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
            teacher.deleteCuratorProfile(); // TODO: придумать название
            teacherRepository.save(teacher);
        }
        curatorRepository.delete(curatorId);
    }
}
