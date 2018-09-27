package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;

@Getter
@Setter
@Accessors(chain = true)
@ToString(of = {"number"})
public class EMAModuleDto implements Comparable {

    private Integer id;

    /**
     * Порядковый номер модуля в УТП
     */
    private Integer number;

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

    @Override
    public int compareTo(Object o) {
        EMAModuleDto other = (EMAModuleDto) o;
//        return other.getNumber().compareTo(this.getNumber());
        return (this.getNumber() < other.getNumber()) ? -1 : ((this.getNumber().equals(other.getNumber())) ? 0 : 1);
    }
}
