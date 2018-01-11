package ru.curriculum.service.etp;

import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;
import ru.curriculum.service.etp.dto.EAModuleDTO;
import ru.curriculum.service.etp.dto.EMAModuleDTO;
import ru.curriculum.service.etp.dto.ETP_DTO;
import ru.curriculum.service.etp.dto.OMAModuleDTO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

@Component
public class ETPFactory {
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
                createEMAModules(etpDTO.getEmaModules()),
                createOMAModules(etpDTO.getOmaModules())
        );
        return null;
    }

    private Set<EAModule> createEAModules(List<EAModuleDTO> dtos) {
        return null;
    }

    private Set<OMAModule> createOMAModules(List<OMAModuleDTO> dtos) {
        return null;
    }

    private Set<EMAModule> createEMAModules(List<EMAModuleDTO> dtos) {
        return null;
    }
}
