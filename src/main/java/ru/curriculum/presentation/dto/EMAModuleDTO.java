package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMASection;
import ru.curriculum.service.etp.dto.PlanDTO;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class EMAModuleDTO {
    private Integer id;
    private String name;
    private Set<EMASectionDTO> sections;

    public EMAModuleDTO() {
        this.sections = new HashSet<>();
    }

    public EMAModuleDTO(EMAModule emaModule) {
        this.id = emaModule.id();
        this.name = emaModule.name();
        this.sections =
                emaModule.sections()
                        .stream()
                        .sorted(Comparator.comparing(EMASection::id))
                        .map(EMASectionDTO::new)
                        .collect(Collectors.toSet());
    }
}
