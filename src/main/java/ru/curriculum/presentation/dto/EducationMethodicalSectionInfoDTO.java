package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EducationMethodicalSectionInfo;

@Getter
@Setter
public class EducationMethodicalSectionInfoDTO {
    private Integer id;
    private String code;
    private String name;

    public EducationMethodicalSectionInfoDTO(EducationMethodicalSectionInfo info) {
        this.id = info.id();
        this.code = info.code();
        this.name = info.name();
    }
}
