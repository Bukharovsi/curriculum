package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.educationActivityModule.EAModule;
import ru.curriculum.domain.etp.entity.educationActivityModule.EASection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
public class EAModuleDTO {
    private Integer id;
    @NotEmpty(message = "Необходимо заполнить поле \"Название модуля\"")
    private String name;
    private List<EASectionDTO> sections;

    public EAModuleDTO() {
        sections = new ArrayList<>();
    }

    public EAModuleDTO(EAModule module) {
        this.id = module.id();
        this.name = module.name();
        this.sections =
                module
                        .eaSections()
                        .stream()
                        .sorted(Comparator.comparing(EASection::id))
                        .map(EASectionDTO::new)
                        .collect(toList());
    }
}
