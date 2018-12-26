package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.service.etp.controller.Row;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
@NoArgsConstructor
public class EAModuleDto extends Row {

    private Integer id;

    private String name;

    private List<EASectionDto> sections = new ArrayList<>();

    public EAModuleDto(Integer number) {
        super(number);
    }

    public EAModuleDto(EAModule module) {
        this.id = module.id();
        this.name = module.name();
        this.number = module.number();
        this.sections =
                module
                        .sections()
                        .stream()
                        .sorted(Comparator.comparing(EASection::number))
                        .map(EASectionDto::new)
                        .collect(toList());
    }
}
