package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;

import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
public class OMAModuleDTO {
    private Integer id;
    private String name;
    private List<OMASectionDTO> sections;

    public OMAModuleDTO() {
        this.sections = new ArrayList<>();
    }

    public OMAModuleDTO(OMAModule omaModule) {
        this.id = omaModule.id();
        this.name = omaModule.name();
        this.sections =
                omaModule.sections()
                        .stream()
                        .map(OMASectionDTO::new)
                        .sorted(Comparator.comparing(OMASectionDTO::getId))
                        .collect(Collectors.toList());
    }
}
