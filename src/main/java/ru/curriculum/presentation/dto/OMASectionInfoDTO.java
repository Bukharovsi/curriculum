package ru.curriculum.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASectionInfo;

@Getter
@Setter
public class OMASectionInfoDTO {
    private Integer id;
    private String code;
    private String name;

    public OMASectionInfoDTO() {
    }

    public OMASectionInfoDTO(OMASectionInfo info) {
        this.id = info.id();
        this.code = info.code();
        this.name = info.name();
    }
}
