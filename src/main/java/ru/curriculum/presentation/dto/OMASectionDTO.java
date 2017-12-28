package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASection;
import ru.curriculum.service.etp.dto.PlanDTO;

@Getter
@Setter
public class OMASectionDTO {
    private Integer id;
    private OMASectionInfoDTO info;
    private PlanDTO plan;

    public OMASectionDTO() {
        this.plan = new PlanDTO();
    }

    public OMASectionDTO(OMASection section) {
        this.id = section.id();
        this.info = new OMASectionInfoDTO(section.info());
        this.plan = new PlanDTO(section.plan());
    }
}
