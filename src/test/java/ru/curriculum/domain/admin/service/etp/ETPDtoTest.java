package ru.curriculum.domain.admin.service.etp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.domain.etp.entity.educationActivity.EATopic;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;
import ru.curriculum.service.etp.dto.*;
import ru.curriculum.service.etp.statusManager.ETPStatus;


public class ETPDtoTest extends Assert {
    private ETPMock etpMock;

    @Before
    public void setUp() {
        etpMock = new ETPMock();
    }

    @Test
    public void createETPDto_defaultValuesInitializedCorrectly() {
        ETPDto dto = new ETPDto();

        assertNotNull(dto.getEaModules());
        assertNotNull(dto.getEmaModules());
        assertNotNull(dto.getOmaModules());
        assertEquals(ETPStatus.DRAFT, dto.getActualStatus());
        assertEquals(ETPStatus.DRAFT, dto.getNewStatus());
    }

    @Test
    public void createETP_DTOFromETP_mustBeCreateCorrectly() {
        ETP etp = etpMock.getETP();

        ETPDto etpDTO = new ETPDto(etp);

        assertEquals(etp, etpDTO);
    }

    @Test
    public void createEAModuleDTOFromETP_mustBeCreateCorrectly() {
        EAModule eaModule = new EAModule("Модуль учебной деятельности", etpMock.getEASections());

        EAModuleDto eaModuleDTO = new EAModuleDto(eaModule);

        assertEquals(eaModule.name(), eaModuleDTO.getName());
        assertEquals(eaModule.id(), eaModuleDTO.getId());
        assertEquals(eaModule.sections().size(), eaModuleDTO.getSections().size());
    }

    @Test
    public void createEASectionDTOFromEASection_mustBeCreateCorrectly() {
        EASection eaSection = new EASection("Раздел учебной деятельности", etpMock.getEATopics());

        EASectionDto eaSectionDto = new EASectionDto(eaSection);

        assertEquals(eaSection.id(), eaSectionDto.getId());
        assertEquals(eaSection.name(), eaSectionDto.getName());
        assertEquals(eaSection.topics().size(), eaSectionDto.getTopics().size());
    }

    @Test
    public void createEATopicDTOFromEATopic_mustBeCreateCorrectly() {
        EATopic eaTopic = new EATopic("Тема раздела модуля учебной деятельности", etpMock.getPlan());

        EATopicDto eaTopicDto = new EATopicDto(eaTopic);

        assertEquals(eaTopic.id(), eaTopicDto.getId());
        assertEquals(eaTopic.name(), eaTopicDto.getName());
    }

    @Test
    public void createEMAModuleDTOFromEMAModule_mustBeCreateCorrectly() {
        EMAModule emaModule = new EMAModule("Модуль учебно-методической деятельности", etpMock.getPlan());

        EMAModuleDto emaModuleDto = new EMAModuleDto(emaModule);

        assertEquals(emaModule.id(), emaModuleDto.getId());
        assertEquals(emaModule.name(), emaModuleDto.getName());
    }

    @Test
    public void createOMAModuleDTOFromOMAModule_mustBeCreateCorrectly() {
        OMAModule omaModule = new OMAModule("Модуль-организационно-методической деятельности", etpMock.getPlan());

        OMAModuleDto omaModuleDto = new OMAModuleDto(omaModule);

        assertEquals(omaModule.id(), omaModuleDto.getId());
        assertEquals(omaModule.name(), omaModuleDto.getName());
    }

    public void assertEquals(ETP etp, ETPDto etpDTO) {
        assertEquals(etp.id(), etpDTO.getId());
        assertEquals(etp.title(), etpDTO.getTitle());
        assertEquals(etp.target(), etpDTO.getTarget());
        assertEquals(etp.distanceLearningBeginDate(), etpDTO.getDistanceLearningBeginDate());
        assertEquals(etp.distanceLearningEndDate(), etpDTO.getDistanceLearningEndDate());
        assertEquals(etp.fullTimeLearningBeginDate(), etpDTO.getFullTimeLearningBeginDate());
        assertEquals(etp.fullTimeLearningEndDate(), etpDTO.getFullTimeLearningEndDate());
        assertEquals(etp.eaModules().size(), etpDTO.getEaModules().size());
        assertEquals(etp.emaModules().size(), etpDTO.getEmaModules().size());
        assertEquals(etp.omaModules().size(), etpDTO.getOmaModules().size());
        assertEquals(etp.financingSource(), etpDTO.getFinancingSource());
    }
}
