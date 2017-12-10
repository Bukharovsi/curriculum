package ru.curriculum.service.teacher;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.curriculum.domain.teacher.AcademicDegree;

@Getter
@Setter
@EqualsAndHashCode(of = "code")
@ToString
public class AcademicDegreeDTO {
    private String code;
    private String name;

    public AcademicDegreeDTO() {}

    public AcademicDegreeDTO(AcademicDegree academicDegree) {
        this.code = academicDegree.code();
        this.name = academicDegree.name();
    }
}
