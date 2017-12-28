package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMASection;
import ru.curriculum.service.etp.dto.PlanDTO;

@Getter
@Setter
public class EMASectionDTO {
    private Integer id;
    private EMASectionInfoDTO info;
    private PlanDTO plan;

    public EMASectionDTO() {
        this.plan = new PlanDTO();
    }

    public EMASectionDTO(EMASection section) {
        this.id = section.id();
        this.info = new EMASectionInfoDTO(section.info());
        this.plan = new PlanDTO(section.plan());
    }
}
