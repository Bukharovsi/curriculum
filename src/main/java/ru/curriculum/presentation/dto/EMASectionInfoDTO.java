package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMSectionInfo;

@Getter
@Setter
public class EMASectionInfoDTO {
    private Integer id;
    private String code;
    private String name;

    public EMASectionInfoDTO() {}

    public EMASectionInfoDTO(EMSectionInfo info) {
        this.id = info.id();
        this.code = info.code();
        this.name = info.name();
    }
}
