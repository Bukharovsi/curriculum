package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;

@Getter
@Setter
public class EMAModuleDto {
    private Integer id;
    @NotEmpty(message = "\"Учебная-методическая деятельность\" необходимо заполнить поле \"Название модуля\"")
    private String name;
    private PlanDto plan;

    public EMAModuleDto() {
        this.plan = new PlanDto();
    }

    public EMAModuleDto(EMAModule module) {
        this.id = module.id();
        this.name = module.name();
        this.plan = new PlanDto(module.plan());
    }
}
