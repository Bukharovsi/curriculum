package ru.curriculum.domain.teacher.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.admin.user.repository.UserRepository;
import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.directories.academicDegree.AcademicDegreeRepository;
import ru.curriculum.domain.directories.staffTable.StaffTable;
import ru.curriculum.domain.directories.staffTable.StaffTableRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.service.teacher.dto.TeacherDTO;

import javax.persistence.EntityNotFoundException;

@Component
public class TeacherFactory {
    @Autowired
    private AcademicDegreeRepository academicDegreeRepository;
    @Autowired
    private StaffTableRepository staffTableRepository;
    @Autowired
    private UserRepository userRepository;

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

        if(null != teacherDTO.getUserId()) {
            User user = userRepository.findOne(teacherDTO.getUserId());
            teacher.assignUserAccount(user);
        }

        return teacher;
    }
}
