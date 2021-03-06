package ru.curriculum.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.teacher.factory.TeacherFactory;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.teacher.dto.TeacherDto;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class TeacherCRUDService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private TeacherFactory teacherFactory;

    public Collection<TeacherDto> findAll() {
        Collection<TeacherDto> dtos = new ArrayList<>();
        teacherRepository.findAll().forEach(teacher -> dtos.add(new TeacherDto(teacher)));

        return dtos;
    }

    public TeacherDto get(Integer teacherId) {
        Teacher teacher = teacherRepository.findOne(teacherId);

        return new TeacherDto(teacher);
    }

    public void create(TeacherDto teacherDto) {
        Teacher teacher = teacherFactory.create(teacherDto);
        teacherRepository.save(teacher);
    }

    public void delete(Integer id) {
        teacherRepository.delete(id);
    }
}
