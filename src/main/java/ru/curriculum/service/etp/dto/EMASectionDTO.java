package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMASection;
import ru.curriculum.service.etp.dto.PlanDTO;

@Getter
@Setter
public class EMASectionDTO {
    private Integer id;
    private String name;
    private PlanDTO plan;

    public EMASectionDTO() {
        this.plan = new PlanDTO();
    }

    public EMASectionDTO(EMASection section) {
        this.id = section.id();
        this.name = section.name();
        this.plan = new PlanDTO(section.plan());
    }
}
