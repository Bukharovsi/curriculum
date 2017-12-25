package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OrganizationallyMethodicalSectionInfo;

@Getter
@Setter
public class OrganizationallyMethodicalSectionInfoDTO {
    private Integer id;
    private String code;
    private String name;

    public OrganizationallyMethodicalSectionInfoDTO(OrganizationallyMethodicalSectionInfo info) {
        this.id = info.id();
        this.code = info.code();
        this.name = info.name();
    }

    public OrganizationallyMethodicalSectionInfoDTO() {
    }
}
