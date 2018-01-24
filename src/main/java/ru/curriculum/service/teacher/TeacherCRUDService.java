package ru.curriculum.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.teacher.factory.TeacherFactory;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.teacher.dto.TeacherDTO;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class TeacherCRUDService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherFactory teacherFactory;

    public Collection<TeacherDTO> findAll() {
        Collection<TeacherDTO> dtos = new ArrayList<>();
        teacherRepository.findAll().forEach(teacher -> dtos.add(new TeacherDTO(teacher)));

        return dtos;
    }

    public TeacherDTO get(Integer teacherId) {
        Teacher teacher = teacherRepository.findOne(teacherId);

        return new TeacherDTO(teacher);
    }

    public void create(TeacherDTO teacherDTO) {
        Teacher teacher = teacherFactory.create(teacherDTO);
        teacherRepository.save(teacher);
    }

    public void delete(Integer id) {
        teacherRepository.delete(id);
    }
}
