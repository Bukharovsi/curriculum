package ru.curriculum.domain.etp.entity.educationMethodicalSection;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * EMASection - Educational Methodical Section Info.
 * Справочная информация по учебно-методическому разделу.
 */
@Entity
@Table(name = "education_methodical_section_info")
@Getter
@Accessors(fluent = true)
public class EMSectionInfo {
    @Id
    private Integer id;
    private String code;
    private String name;

    public EMSectionInfo() {
    }
}
