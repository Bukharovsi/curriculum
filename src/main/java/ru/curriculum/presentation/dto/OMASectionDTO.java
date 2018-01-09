package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMASection;
import ru.curriculum.service.etp.dto.PlanDTO;

@Getter
@Setter
public class OMASectionDTO {
    private Integer id;
    private String name;
    private PlanDTO plan;

    public OMASectionDTO() {
        this.plan = new PlanDTO();
    }

    public OMASectionDTO(OMASection omaSection) {
        this.id = omaSection.id();
        this.name = omaSection.name();
        this.plan = new PlanDTO(omaSection.plan());
    }
}
