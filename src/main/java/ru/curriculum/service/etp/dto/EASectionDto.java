package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.domain.etp.entity.educationActivity.EATopic;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class EASectionDto {
    private Integer id;
    @NotEmpty(message = "\"Учебно деятельность\" необходимо заполнить поле \"Название раздела\"")
    private String name;
    @Valid
    private List<EATopicDto> topics;

    public EASectionDto() {
        this.topics = new ArrayList<>();
    }

    public EASectionDto(EASection section) {
        this();
        this.id = section.id();
        this.name = section.name();
        this.topics = section.topics()
                .stream()
                .sorted(Comparator.comparing(EATopic::id))
                .map(EATopicDto::new)
                .collect(Collectors.toList());
    }
}
