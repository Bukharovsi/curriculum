package ru.curriculum.domain.teacher;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import ru.curriculum.service.teacher.AcademicDegreeDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "academic_degree")
@EqualsAndHashCode(of = "code")
@ToString
public class AcademicDegree {
    @Id
    private String code;
    private String name;

    public AcademicDegree() {
    }

    public AcademicDegree(AcademicDegreeDTO academicDegree) {
        this.code = academicDegree.getCode();
        this.name = academicDegree.getName();
    }

    public String code() {
        return code;
    }

    public String name() {
        return name;
    }
}
