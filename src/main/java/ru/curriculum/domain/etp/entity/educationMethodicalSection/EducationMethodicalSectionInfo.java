package ru.curriculum.domain.etp.entity.educationMethodicalSection;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "education_methodical_section_info")
@Getter
@Accessors(fluent = true)
public class EducationMethodicalSectionInfo {
    @Id
    private Integer id;
    private String code;
    private String name;

    public EducationMethodicalSectionInfo() {
    }
}
