package ru.curriculum.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.repository.EducationMethodicalSectionInfoRepository;
import ru.curriculum.domain.etp.repository.OrganizationallyMethodicalSectionInfoRepository;
import ru.curriculum.presentation.dto.EducationMethodicalSectionDTO;
import ru.curriculum.presentation.dto.EducationMethodicalSectionInfoDTO;
import ru.curriculum.presentation.dto.OrganizationallyMethodicalSectionDTO;
import ru.curriculum.presentation.dto.OrganizationallyMethodicalSectionInfoDTO;
import ru.curriculum.service.etp.dto.ETP_DTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class ETP_DTOFactory {
    @Autowired
    private EducationMethodicalSectionInfoRepository educationMethodicalSectionInfoRepository;
    @Autowired
    private OrganizationallyMethodicalSectionInfoRepository organizationallyMethodicalSectionInfoRepository;

    public ETP_DTO createEmptyETP_DTO() {
        ETP_DTO dto = new ETP_DTO();
        dto.setEdMetSec(createEmptyMethodicalSectionsInfo());
        dto.setOrgMetSect(createEmptyOrganizationallyMethodicalSections());

        return dto;
    }

    private List<OrganizationallyMethodicalSectionDTO> createEmptyOrganizationallyMethodicalSections() {
        List<OrganizationallyMethodicalSectionDTO> sections = new ArrayList<>();
        organizationallyMethodicalSectionInfoRepository.findAll().forEach(info -> {
            OrganizationallyMethodicalSectionDTO dto = new OrganizationallyMethodicalSectionDTO();
            dto.setInfo(new OrganizationallyMethodicalSectionInfoDTO(info));
            sections.add(dto);
        });

        return sections;
    }

    private List<EducationMethodicalSectionDTO> createEmptyMethodicalSectionsInfo() {
        List<EducationMethodicalSectionDTO> sections = new ArrayList<>();
        educationMethodicalSectionInfoRepository.findAll().forEach(info -> {
            EducationMethodicalSectionDTO dto = new EducationMethodicalSectionDTO();
            dto.setInfo(new EducationMethodicalSectionInfoDTO(info));
            sections.add(dto);
        });

        return sections;
    }
}
