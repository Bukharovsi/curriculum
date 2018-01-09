package ru.curriculum.domain.admin.domain.etp;

import boot.IntegrationBoot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.presentation.ETP_DTOFactory;
import ru.curriculum.service.etp.dto.EAModuleDTO;
import ru.curriculum.service.etp.dto.EASectionDTO;
import ru.curriculum.service.etp.dto.ETP_DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//public class EPTFactoryTest extends IntegrationBoot {
//    @Autowired
//    private ETPFactory factory;
//    @Autowired
//    private ETP_DTOFactory dtoFactory;
//
//    @Test
//    public void createETP() {
//        ETP_DTO etpDTO = getETP_DTO();
//
//        ETP etp = factory.create(etpDTO);
//
//        assertEquals(etpDTO.getTitle(), etp.title());
//        assertEquals(etpDTO.getTarget(), etp.target());
//        assertEquals(etpDTO.getDistanceLearningBeginDate(), etp.distanceLearningBeginDate());
//        assertEquals(etpDTO.getDistanceLearningEndDate(), etp.distanceLearningEndDate());
//        assertEquals(etpDTO.getFullTimeLearningBeginDate(), etp.fullTimeLearningBeginDate());
//        assertEquals(etpDTO.getFullTimeLearningEndDate(), etp.fullTimeLearningEndDate());
//        assertEquals(etpDTO.getEaModules().size(), etp.eaModules().size());
//        assertEquals(etpDTO.getEmaModules().size(), etp.emaSections().size());
//        assertEquals(etpDTO.getOmaModules().size(), etp.omaSections().size());
//
//        etp.omaSections().forEach(omaSection -> {
//            assertNotNull(omaSection.info());
//            assertNotNull(omaSection.plan());
//            assertNotNull(omaSection.plan());
//        });
//
//        etp.eaModules().forEach(eaModule -> {
//            eaModule.eaSections().forEach(eaSection ->
//                assertEquals("Раздель учебного модуля", eaSection.name())
//            );
//            assertEquals("Учебный модуль", eaModule.name());
//        });
//
//
//        etp.emaSections().forEach(emaSection -> {
//            assertNotNull(emaSection.info());
//            assertNotNull(emaSection.plan());
//            assertNotNull(emaSection.etp());
//        });
//    }
//
//    public ETP_DTO getETP_DTO() {
//        ETP_DTO dto = dtoFactory.createEmptyETP_DTO();
//        dto.setTitle("Тема УТП");
//        dto.setTarget("Цель УТП");
//        dto.setDistanceLearningBeginDate(new Date(1));
//        dto.setDistanceLearningEndDate(new Date(2));
//        dto.setFullTimeLearningBeginDate(new Date(3));
//        dto.setDistanceLearningEndDate(new Date(4));
//        dto.setEaModules(getEAModulesDTO());
//
//        return dto;
//    }
//
//    private List<EAModuleDTO> getEAModulesDTO() {
//        List<EASectionDTO> eaSectionDTOs = new ArrayList<>();
//        EASectionDTO eaSectionDTO = new EASectionDTO();
//        eaSectionDTO.setName("Раздель учебного модуля");
//        eaSectionDTOs.add(eaSectionDTO);
//
//        EAModuleDTO eaModuleDTO = new EAModuleDTO();
//        eaModuleDTO.setName("Учебный модуль");
//        eaModuleDTO.setSections(eaSectionDTOs);
//
//        return new ArrayList<EAModuleDTO>(){{ add(eaModuleDTO); }};
//    }
//}
