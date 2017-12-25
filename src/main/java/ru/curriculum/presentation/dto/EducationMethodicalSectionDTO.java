package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EducationMethodicalSection;
import ru.curriculum.service.etp.dto.PlanDTO;

@Getter
@Setter
public class EducationMethodicalSectionDTO {
    private Integer id;
    private EducationMethodicalSectionInfoDTO info;
    private PlanDTO plan;

    public EducationMethodicalSectionDTO() {
        this.plan = new PlanDTO();
    }

    public EducationMethodicalSectionDTO(EducationMethodicalSection section) {
        this.id = section.id();
        this.info = new EducationMethodicalSectionInfoDTO(section.info());
        this.plan = new PlanDTO(section.plan());
    }
}
