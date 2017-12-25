package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OrganizationallyMethodicalSection;
import ru.curriculum.service.etp.dto.PlanDTO;

@Getter
@Setter
public class OrganizationallyMethodicalSectionDTO {
    private Integer id;
    private OrganizationallyMethodicalSectionInfoDTO info;
    private PlanDTO plan;

    public OrganizationallyMethodicalSectionDTO() {
        this.plan = new PlanDTO();
    }

    public OrganizationallyMethodicalSectionDTO(OrganizationallyMethodicalSection section) {
        this.id = section.id();
        this.info = new OrganizationallyMethodicalSectionInfoDTO(section.info());
        this.plan = new PlanDTO(section.plan());
    }
}
