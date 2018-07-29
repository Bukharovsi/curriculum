package ru.curriculum.service.curator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.curator.converter.DtoToCuratorConverter;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.curator.exception.CrudOperationException;
import ru.curriculum.service.curator.exception.CuratorNotFoundException;

import java.util.ArrayList;
import java.util.Collection;


@Component
public class CuratorCRUDService {
    private CuratorRepository curatorRepository;
    private TeacherRepository teacherRepository;
    private DtoToCuratorConverter dtoToCuratorConverter;

    @Autowired
    public CuratorCRUDService(
            CuratorRepository curatorRepository,
            TeacherRepository teacherRepository,
            DtoToCuratorConverter dtoToCuratorConverter
    ) {
        this.curatorRepository = curatorRepository;
        this.teacherRepository = teacherRepository;
        this.dtoToCuratorConverter = dtoToCuratorConverter;
    }

    public Collection<CuratorDto> findAllCurators() {
        Collection<CuratorDto> curatorDtos = new ArrayList<>();
        curatorRepository.findAll().forEach(curator ->
                curatorDtos.add(new CuratorDto(curator))
        );

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

    @SuppressWarnings("ConstantConditions")
    public void delete(Integer curatorId) {
        Curator  curator = curatorRepository.findOne(curatorId);
        if (null == curator) {
            throw new CrudOperationException(
                    String.format("Попытка удалить несуществующего куратора, id=%s", curatorId)
            );
        }

        if (curator.isAdmin()) {
            throw new CrudOperationException("Куратора с логином \"admin\" нельзя удалить, является системным");
        }

        Teacher teacher = teacherRepository.findByCuratorId(curatorId);
        if(null != teacher) {
            teacher.deleteCuratorProfile();
            teacherRepository.save(teacher);
        }

        curatorRepository.delete(curatorId);
    }
}
