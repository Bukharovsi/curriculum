package ru.curriculum.domain.teacher;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import ru.curriculum.service.teacher.AcademicDegreeDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "academic_degree")
@EqualsAndHashCode(of = "code")
@Getter
@Accessors(fluent = true)
public class AcademicDegree {
    @Id
    private String code;
    private String name;

    public AcademicDegree() {
    }

    public AcademicDegree(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
