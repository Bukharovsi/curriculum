package ru.curriculum.presentation;

import org.springframework.stereotype.Component;
import ru.curriculum.presentation.dto.EMAModuleDTO;
import ru.curriculum.presentation.dto.OMAModuleDTO;
import ru.curriculum.service.etp.dto.ETP_DTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ETP_DTOFactory {

    public ETP_DTO createEmptyETP_DTO() {
        ETP_DTO dto = new ETP_DTO();
        dto.setEmaModules(createEmptyEMASectionsInfo());
        dto.setOmaModules(createEmptyOMASections());

        return dto;
    }

    private List<OMAModuleDTO> createEmptyOMASections() {
        List<OMAModuleDTO> sections = new ArrayList<>();

        return sections;
    }

    private List<EMAModuleDTO> createEmptyEMASectionsInfo() {
        List<EMAModuleDTO> sections = new ArrayList<>();

        return sections;
    }
}
