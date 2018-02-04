package ru.curriculum.service.directories.academicDegree;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.directories.academicDegree.AcademicDegree;


@Getter
@Setter
public class AcademicDegreeDto {
    private String code;
    private String name;

    public AcademicDegreeDto() {}

    public AcademicDegreeDto(AcademicDegree academicDegree) {
        this.code = academicDegree.code();
        this.name = academicDegree.name();
    }
}
