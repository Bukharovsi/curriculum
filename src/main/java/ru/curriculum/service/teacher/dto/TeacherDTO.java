package ru.curriculum.service.teacher.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.teacher.entity.Teacher;

@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class TeacherDTO {
    private Integer id;
    @NotEmpty
    private String surname;
    @NotEmpty(message = "{errors.notEmpty}")
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty
    private String academicDegreeCode;
    private String academicDegreeName;
    private String placeOfWork;
    private String position;
    private String username;
    private Integer userId;
    private boolean hasAccount;

    public TeacherDTO() {
        this.placeOfWork = "ГАОУ ДПО Институт Развития Образования РТ";
        this.hasAccount = false;
    }

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.id();
        this.surname = teacher.surname();
        this.firstname = teacher.firstname();
        this.lastname = teacher.lastname();
        this.academicDegreeCode = teacher.academicDegree().code();
        this.academicDegreeName = teacher.academicDegree().name();
        this.placeOfWork = teacher.placeOfWork();
        this.position = teacher.position();
        this.userId = teacher.hasUserAccount() ? teacher.userAccount().id() : null;
        this.username = teacher.hasUserAccount() ? teacher.user().username() : "";
    }
}
