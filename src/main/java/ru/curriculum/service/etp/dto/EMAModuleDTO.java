package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;

@Getter
@Setter
public class EMAModuleDTO {
    private Integer id;
    private String name;
    private PlanDTO plan;

    public EMAModuleDTO() {
        this.plan = new PlanDTO();
    }

    public EMAModuleDTO(EMAModule module) {
        this.id = module.id();
        this.name = module.name();
        this.plan = new PlanDTO(module.plan());
    }
}
