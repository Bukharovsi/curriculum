package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.domain.etp.entity.educationActivity.EATopic;
import ru.curriculum.service.etp.controller.Row;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class EASectionDto extends Row {

    private Integer id;

    private String name;

    private List<EATopicDto> topics = new ArrayList<>();

    public EASectionDto(Integer number) {
        super(number);
    }

    public EASectionDto(EASection section) {
        this.id = section.id();
        this.name = section.name();
        this.number = section.number();
        this.topics = section.topics()
                .stream()
                .sorted(Comparator.comparing(EATopic::number))
                .map(EATopicDto::new)
                .collect(Collectors.toList());
    }
}
