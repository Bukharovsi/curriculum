package ru.curriculum.domain.helper;

import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.entity.TeacherType;
import ru.curriculum.service.teacher.dto.TeacherDto;

public class TeacherHelper {

    public TeacherDto getTeacherDTO() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(1);
        teacherDto.setSurname("Иванов");
        teacherDto.setFirstName("Иван");
        teacherDto.setPatronymic("Иванович");
        teacherDto.setAcademicDegreeCode("ph_d");
        teacherDto.setAcademicDegreeName("Доктор наук");
        teacherDto.setPlaceOfWork("ИРОРТ");
        return teacherDto;
    }

    public Teacher getTeacher() {
        return new Teacher(
                1,
                "Иванов",
                "Иван",
                "Иванович",
                new AcademicDegree("ph_d", "Доктор наук"),
                "Макдоналдс",
                "Жарщик котлет",
                TeacherType.STAFF
        );
    }
}
