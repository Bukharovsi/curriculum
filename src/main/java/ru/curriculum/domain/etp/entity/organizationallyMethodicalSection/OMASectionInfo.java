package ru.curriculum.domain.etp.entity.organizationallyMethodicalSection;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * OMASectionInfo - organizationally methodical activity section.
 * Справочная информация по разделам организационно-методической деятельности.
 */
@Entity
@Table(name = "organizationally_methodical_section_info")
@Getter
@Accessors(fluent = true)
public class OMASectionInfo {
    @Id
    private Integer id;
    private String code;
    private String name;

    public OMASectionInfo() {
    }
}
