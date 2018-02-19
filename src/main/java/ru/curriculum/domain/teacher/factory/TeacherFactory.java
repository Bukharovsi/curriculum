package ru.curriculum.domain.teacher.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.directories.academicDegree.AcademicDegreeRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.service.teacher.dto.TeacherDto;

import javax.persistence.EntityNotFoundException;

@Component
public class TeacherFactory {
    @Autowired
    private AcademicDegreeRepository academicDegreeRepository;
    @Autowired
    private CuratorRepository curatorRepository;

    public Teacher create(TeacherDto teacherDto) {
        AcademicDegree academicDegree = academicDegreeRepository.findOne(teacherDto.getAcademicDegreeCode());

        if(null == academicDegree) {
            String errorMessage = String.format(
                    "Ученая степень с кодом \"%s\" не заведена в системе",
                    teacherDto.getAcademicDegreeCode());
            throw new EntityNotFoundException(errorMessage);
        }

        Teacher teacher = new Teacher(
                teacherDto.getId(),
                teacherDto.getSurname(),
                teacherDto.getFirstName(),
                teacherDto.getPatronymic(),
                academicDegree,
                teacherDto.getPlaceOfWork(),
                teacherDto.getPositionHeld(),
                teacherDto.getType()
        );

        if(null != teacherDto.getCuratorId()) {
            Curator curator = curatorRepository.findOne(teacherDto.getCuratorId());
            teacher.assignCuratorProfile(curator);
        }

        return teacher;
    }
}
