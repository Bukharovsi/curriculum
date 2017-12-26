package ru.curriculum.domain.etp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.etp.entity.educationActivityModule.EAModule;
import ru.curriculum.domain.etp.entity.educationActivityModule.EASection;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMASection;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASection;
import ru.curriculum.domain.etp.repository.EMASectionInfoRepository;
import ru.curriculum.domain.etp.repository.OMASectionInfoRepository;
import ru.curriculum.presentation.dto.EMASectionDTO;
import ru.curriculum.presentation.dto.OMASectionDTO;
import ru.curriculum.service.etp.PlanFactory;
import ru.curriculum.service.etp.dto.ETP_DTO;
import ru.curriculum.service.etp.dto.EAModuleDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ETPFactory {
    @Autowired
    private EMASectionInfoRepository emaSectionInfoRepository;
    @Autowired
    private OMASectionInfoRepository omaSectionInfoRepository;
    @Autowired
    private PlanFactory planFactory;

    public ETP create(ETP_DTO etpDTO) {
        ETP etp = new ETP(
                etpDTO.getId(),
                etpDTO.getTitle(),
                etpDTO.getTarget(),
                etpDTO.getDistanceLearningBeginDate(),
                etpDTO.getDistanceLearningEndDate(),
                etpDTO.getFullTimeLearningBeginDate(),
                etpDTO.getFullTimeLearningEndDate(),
                createEAModules(etpDTO.getEaModules()),
                createEMASections(etpDTO.getEmaSections()),
                createOMASections(etpDTO.getOmaSections()));

        return etp;
    }

    private Set<EMASection> createEMASections(List<EMASectionDTO> edMetSec) {
        Set<EMASection> sections = new HashSet<>();
        edMetSec.forEach(sectionDTO -> {
            EMASection section = new EMASection(
                    sectionDTO.getId(),
                    emaSectionInfoRepository.findOne(sectionDTO.getInfo().getId()),
                    new Plan(sectionDTO.getPlan()));
            sections.add(section);
        });

        return sections;
    }

    private Set<OMASection> createOMASections(List<OMASectionDTO> orgMetSec) {
        Set<OMASection> sections = new HashSet<>();
        orgMetSec.forEach(sectionDTO -> {
            OMASection section = new OMASection(
                    sectionDTO.getId(),
                    omaSectionInfoRepository.findOne(sectionDTO.getInfo().getId()),
                    planFactory.create(sectionDTO.getPlan()));
            sections.add(section);
        });

        return sections;
    }

    private Set<EAModule> createEAModules(List<EAModuleDTO> moduleDTOs) {
        Set<EAModule> modules = new HashSet<>();
        moduleDTOs.forEach(moduleDTO -> {
            Set<EASection> sectionDTOs = new HashSet<>();
            moduleDTO.getSections().forEach(sectionDTO-> {
                    EASection section = new EASection(
                            sectionDTO.getId(),
                            sectionDTO.getName(),
                            new Plan(sectionDTO.getPlan()));
                    sectionDTOs.add(section);
            });
            EAModule module = new EAModule(moduleDTO.getId(), moduleDTO.getName(), sectionDTOs);
            modules.add(module);
        });

        return modules;
    }
}
