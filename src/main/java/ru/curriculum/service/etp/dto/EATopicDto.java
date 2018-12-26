package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationActivity.EATopic;
import ru.curriculum.service.etp.controller.Row;

@Getter
@Setter
public class EATopicDto extends Row {

    private Integer id;

    private String name;

    private PlanDto plan;

    public EATopicDto() {
        this.plan = new PlanDto();
    }

    public EATopicDto(Integer number) {
        super(number);
        this.plan = new PlanDto();
    }

    public EATopicDto(EATopic topic) {
        this.id = topic.id();
        this.name = topic.name();
        this.plan = new PlanDto(topic.plan());
        this.number = topic.number();
    }
}
