package ru.curriculum.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.teacher.AcademicDegree;
import ru.curriculum.domain.teacher.AcademicDegreeRepository;
import ru.curriculum.domain.teacher.Teacher;

import javax.persistence.EntityNotFoundException;

@Component
public class TeacherFactory {
    @Autowired
    private AcademicDegreeRepository academicDegreeRepository;

    public Teacher create(TeacherDTO teacherDTO) {
        AcademicDegree academicDegree = academicDegreeRepository.findOne(teacherDTO.getAcademicDegreeCode());
        if(null == academicDegree) {
            String errorMessage = String.format(
                    "Ученая степень с кодом \"%s\" не заведена в системе",
                    teacherDTO.getAcademicDegreeCode());
            throw new EntityNotFoundException(errorMessage);
        }
        Teacher teacher = new Teacher(
                teacherDTO.getId(),
                teacherDTO.getSurname(),
                teacherDTO.getFirstname(),
                teacherDTO.getLastname(),
                academicDegree,
                teacherDTO.getPlaceOfWork(),
                teacherDTO.getPosition());

        return teacher;
    }
}
