package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.service.etp.controller.Row;


@Getter
@Setter
@Accessors(chain = true)
public class EMAModuleDto extends Row {

    private Integer id;

    private String name;

    private PlanDto plan;

    public EMAModuleDto() {
        this.plan = new PlanDto();
    }

    public EMAModuleDto(Integer number) {
        super(number);
        this.plan = new PlanDto();
    }

    public EMAModuleDto(EMAModule module) {
        this.id = module.id();
        this.name = module.name();
        this.plan = new PlanDto(module.plan());
        this.number = module.number();
    }
}
