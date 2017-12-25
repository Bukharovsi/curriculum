package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.etp.entity.EducationActivityModule;
import ru.curriculum.domain.etp.entity.EducationActivitySection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
public class EducationActivityModuleDTO {
    private Integer id;
    @NotEmpty(message = "Необходимо заполнить поле \"Название модуля\"")
    private String name;
    private List<EducationActivitySectionDTO> sections;

    public EducationActivityModuleDTO() {
        sections = new ArrayList<>();
    }

    public EducationActivityModuleDTO(EducationActivityModule module) {
        this.id = module.id();
        this.name = module.name();
        this.sections =
                module
                        .educationActivitySections()
                        .stream()
                        .sorted(Comparator.comparing(EducationActivitySection::id))
                        .map(EducationActivitySectionDTO::new)
                        .collect(toList());
    }
}
