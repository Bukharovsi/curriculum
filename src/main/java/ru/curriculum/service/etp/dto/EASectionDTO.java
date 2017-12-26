package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.educationActivityModule.EASection;

@Getter
@Setter
public class EASectionDTO {
    private Integer id;
    @NotEmpty(message = "Необходими заполнить поле \"Дата начала дистанционного обучения\"")
    private String name;
    private PlanDTO plan;

    public EASectionDTO() {
        this.plan = new PlanDTO();
    }

    public EASectionDTO(EASection section) {
        this.id = section.id();
        this.name = section.name();
        this.plan = new PlanDTO(section.plan());
    }
}
