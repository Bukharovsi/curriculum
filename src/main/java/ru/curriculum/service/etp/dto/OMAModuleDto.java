package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;

@Getter
@Setter
public class OMAModuleDto {
    private Integer id;
    @NotEmpty(message = "\"Организационно-методическая деятельность\" необходимо заполнить поле \"Название модуля\"")
    private String name;
    private PlanDto plan;

    public OMAModuleDto() {
        this.plan = new PlanDto();
    }

    public OMAModuleDto(OMAModule module) {
        this.id = module.id();
        this.name = module.name();
        this.plan = new PlanDto(module.plan());
    }
}
