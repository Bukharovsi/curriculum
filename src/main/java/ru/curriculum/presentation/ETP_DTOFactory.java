package ru.curriculum.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.repository.EMASectionInfoRepository;
import ru.curriculum.domain.etp.repository.OMASectionInfoRepository;
import ru.curriculum.presentation.dto.EMASectionDTO;
import ru.curriculum.presentation.dto.EMASectionInfoDTO;
import ru.curriculum.presentation.dto.OMASectionDTO;
import ru.curriculum.presentation.dto.OMASectionInfoDTO;
import ru.curriculum.service.etp.dto.ETP_DTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ETP_DTOFactory {
    @Autowired
    private EMASectionInfoRepository emaSectionInfoRepository;
    @Autowired
    private OMASectionInfoRepository omaSectionInfoRepository;

    public ETP_DTO createEmptyETP_DTO() {
        ETP_DTO dto = new ETP_DTO();
        dto.setEmaSections(createEmptyMethodicalSectionsInfo());
        dto.setOmaSections(createEmptyOrganizationallyMethodicalSections());

        return dto;
    }

    private List<OMASectionDTO> createEmptyOrganizationallyMethodicalSections() {
        List<OMASectionDTO> sections = new ArrayList<>();
        omaSectionInfoRepository.findAllByOrderById()
                .forEach(info -> {
                    OMASectionDTO dto = new OMASectionDTO();
                    dto.setInfo(new OMASectionInfoDTO(info));
                    sections.add(dto);
                });

        return sections;
    }

    private List<EMASectionDTO> createEmptyMethodicalSectionsInfo() {
        List<EMASectionDTO> sections = new ArrayList<>();
        emaSectionInfoRepository.findAllByOrderById()
                .forEach(info -> {
                    EMASectionDTO dto = new EMASectionDTO();
                    dto.setInfo(new EMASectionInfoDTO(info));
                    sections.add(dto);
                });

        return sections;
    }
}
