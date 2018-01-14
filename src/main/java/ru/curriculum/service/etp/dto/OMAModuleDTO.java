package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;

@Getter
@Setter
public class OMAModuleDTO {
    private Integer id;
    private String name;
    private PlanDTO plan;

    public OMAModuleDTO() {
        this.plan = new PlanDTO();
    }

    public OMAModuleDTO(OMAModule module) {
        this.id = module.id();
        this.name = module.name();
        this.plan = new PlanDTO(module.plan());
    }
}
