package ru.curriculum.domain.admin.service.etp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.service.etp.dto.ETP_DTO;

@Ignore // Для создания теперь используем фабрику
public class ETP_DTOTest extends Assert {
//    private ETPMock etpMock;
//
//    @Before
//    public void setUp() {
//        etpMock = new ETPMock();
//    }
//
//    @Test
//    public void createETP_DTOfromETP_mustBeCreateCorrectly() {
//        ETP etp = etpMock.getETP();
//        etp.addModule(etpMock.getFullEducationModule());
//
//        ETP_DTO etpDTO = new ETP_DTO(etp);
//
//        assertEquals(etp, etpDTO);
//    }
//
//    @Test
//    public void createETPfromETP_DTO_mustBeCreateCorrectly() {
//        ETP_DTO etpDTO = etpMock.getETP_DTO();
//        ETP etp = new ETP(etpDTO);
//
//        assertEquals(etp, etpDTO);
//    }
//
//    public void assertEquals(ETP etp, ETP_DTO etpDTO) {
//        assertEquals(etp.id(), etpDTO.getId());
//        assertEquals(etp.title(), etpDTO.getTitle());
//        assertEquals(etp.target(), etpDTO.getTarget());
//        assertEquals(etp.distanceLearningBeginDate(), etpDTO.getDistanceLearningBeginDate());
//        assertEquals(etp.distanceLearningEndDate(), etpDTO.getDistanceLearningEndDate());
//        assertEquals(etp.fullTimeLearningBeginDate(), etpDTO.getFullTimeLearningBeginDate());
//        assertEquals(etp.fullTimeLearningEndDate(), etpDTO.getFullTimeLearningEndDate());
//        assertEquals(etpMock.getFullEducationModule().id(), etpDTO.getEaModules().get(0).getId());
//        assertEquals(etpMock.getFullEducationModule().name(), etpDTO.getEaModules().get(0).getName());
//        assertEquals(etpMock.getSection().id(), etpDTO.getEaModules().get(0).getSections().get(0).getId());
//        assertEquals(etpMock.getSection().name(), etpDTO.getEaModules().get(0).getSections().get(0).getName());
//    }
}
