package ru.curriculum.service.teacher;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.domain.teacher.AcademicDegree;
import ru.curriculum.domain.teacher.Teacher;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class TeacherDTO {
    private Integer id;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String firstname;
    private String lastname;
    private AcademicDegree academicDegree;
    private String placeOfWork;
    private String position;
    private Integer userId;

    public TeacherDTO() {

    }

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.id();
        this.surname = teacher.surname();
        this.firstname = teacher.firstname();
        this.lastname = teacher.lastname();
        //TODO: При использовании ДТО не работат, разобраться как можно сделать конвертацию при селекте
//        this.academicDegree = new AcademicDegreeDTO(teacher.academicDegree());
        this.academicDegree = teacher.academicDegree();
        this.placeOfWork = teacher.placeOfWork();
        this.position = teacher.position();
        this.userId = teacher.isUser() ? teacher.user().id() : null;
    }
}
