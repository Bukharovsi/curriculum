package ru.curriculum.service.etp;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ModuleDTO {
    private String title;
    private List<SectionDTO> sections;

    public ModuleDTO() {
        sections = new ArrayList<>();
    }
}
