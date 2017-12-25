package ru.curriculum.domain.etp.entity.organizationallyMethodicalSection;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "organizationally_methodical_section_info")
@Getter
@Accessors(fluent = true)
public class OrganizationallyMethodicalSectionInfo {
    @Id
    private Integer id;
    private String code;
    private String name;

    public OrganizationallyMethodicalSectionInfo() {
    }
}
