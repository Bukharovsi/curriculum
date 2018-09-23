package ru.curriculum.service.etp.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.VolumeInHours;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.domain.etp.entity.educationActivity.EATopic;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;
import ru.curriculum.service.etp.dto.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class ETPDtoToEtpConverter {
    @Autowired
    private PlanFactory planFactory;

    public ETP convert(ETPDto etpDto) {
        ETP etp = new ETP(
                etpDto.getId(),
                etpDto.getTitle(),
                etpDto.getTarget(),
                etpDto.getBeginDate(),
                etpDto.getEndDate(),
                etpDto.getDistanceLearningBeginDate(),
                etpDto.getDistanceLearningEndDate(),
                etpDto.getFullTimeLearningBeginDate(),
                etpDto.getFullTimeLearningEndDate(),
                etpDto.getActualStatus(),
                etpDto.getLernerCount(),
                etpDto.getSchoolDaysCount(),
                etpDto.getFinancingSource(),
                toVolumeInHours(etpDto.getVolumeInHours()),
                createEAModules(etpDto.getEaModules()),
                createEMAModules(etpDto.getEmaModules()),
                createOMAModules(etpDto.getOmaModules())
        );
        etp.stateProgramId(etpDto.getStateProgramId());

        return etp;
    }

    private VolumeInHours toVolumeInHours(VolumeInHoursDto dto) {
        return VolumeInHours.builder()
                .total(dto.getTotal())
                .perOneListener(dto.getPerOneListener())
                .fullTimePerOneListener(dto.getFullTimePerOneListener())
                .distancePerOneListener(dto.getDistancePerOneListener())
                .totalStudyWork(dto.getTotalStudyWork())
                .fullTimeStudyWork(dto.getFullTimeStudyWork())
                .distanceStudyWork(dto.getDistanceStudyWork())
                .paymentStudyWork(dto.getPaymentStudyWork())
                .fullTimePaymentForStudyWork(dto.getFullTimePaymentForStudyWork())
                .distancePaymentForStudyWork(dto.getDistancePaymentForStudyWork())
                .emaPaymentForStudyWork(dto.getEmaPaymentForStudyWork())
                .omaPaymentForStudyWork(dto.getOmaPaymentForStudyWork())
                .inLoad(dto.getInLoad())
                .fullTimeInLoad(dto.getFullTimeInLoad())
                .distanceInLoad(dto.getDistanceInLoad())
                .emaInLoad(dto.getEmaInLoad())
                .omaInLoad(dto.getOmaInLoad())
                .build();
    }

    private Set<EAModule> createEAModules(List<EAModuleDto> dtos) {
        Set<EAModule> eaModules = new HashSet<>();
        dtos.forEach(dto -> {
            EAModule module = new EAModule(
                    dto.getId(), dto.getName(), createEASections(dto.getSections())
            );
            eaModules.add(module);
        });

        return eaModules;
    }

    private Set<EASection> createEASections(List<EASectionDto> dtos) {
        Set<EASection> eaSections = new HashSet<>();
        dtos.forEach(dto -> {
            EASection section = new EASection(
                    dto.getId(), dto.getName(), createTopics(dto.getTopics())
            );
            eaSections.add(section);
        });

        return eaSections;
    }

    private Set<EATopic> createTopics(List<EATopicDto> dtos) {
        Set<EATopic> topics = new HashSet<>();
        dtos.forEach(dto -> {
            EATopic topic = new EATopic(
                    dto.getId(), dto.getName(), planFactory.create(dto.getPlan())
            );
            topics.add(topic);
        });

        return topics;
    }

    private Set<OMAModule> createOMAModules(List<OMAModuleDto> dtos) {
        Set<OMAModule> omaModules = new HashSet<>();
        dtos.forEach(dto -> {
            OMAModule module = new OMAModule(
                    dto.getId(), dto.getName(), planFactory.create(dto.getPlan())
            );
            omaModules.add(module);
        });

        return omaModules;
    }

    private Set<EMAModule> createEMAModules(List<EMAModuleDto> dtos) {
        Set<EMAModule> emaSections = new HashSet<>();
        dtos.forEach(dto -> {
            EMAModule module = new EMAModule(
                    dto.getId(), dto.getName(), planFactory.create(dto.getPlan())
            );
            emaSections.add(module);
        });

        return emaSections;
    }
}
