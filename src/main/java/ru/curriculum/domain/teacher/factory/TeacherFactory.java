package ru.curriculum.domain.teacher.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.directories.academicDegree.AcademicDegreeRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.service.teacher.dto.TeacherDTO;

import javax.persistence.EntityNotFoundException;

@Component
public class TeacherFactory {
    @Autowired
    private AcademicDegreeRepository academicDegreeRepository;
    @Autowired
    private CuratorRepository curatorRepository;

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
                teacherDTO.getFirstName(),
                teacherDTO.getPatronymic(),
                academicDegree,
                teacherDTO.getPlaceOfWork(),
                teacherDTO.getPositionHeld()
        );

        if(null != teacherDTO.getCuratorId()) {
            Curator curator = curatorRepository.findOne(teacherDTO.getCuratorId());
            teacher.assignCuratorProfile(curator);
        }

        return teacher;
    }
}
