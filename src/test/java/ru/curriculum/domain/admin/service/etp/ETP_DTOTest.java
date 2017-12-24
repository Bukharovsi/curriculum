package ru.curriculum.domain.admin.service.etp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.service.etp.dto.ETP_DTO;

public class ETP_DTOTest extends Assert {
    private ETPMock etpMock;

    @Before
    public void setUp() {
        etpMock = new ETPMock();
    }

    @Test
    public void createETP_DTOfromETP_mustBeCreateCorrectly() {
        ETP etp = etpMock.getETP();
        etp.addModule(etpMock.getFullEducationModule());

        ETP_DTO etpDTO = new ETP_DTO(etp);

        assertEquals(etp, etpDTO);
    }

    @Test
    public void createETPfromETP_DTO_mustBeCreateCorrectly() {
        ETP_DTO etpDTO = etpMock.getETP_DTO();
        ETP etp = new ETP(etpDTO);

        assertEquals(etp, etpDTO);
    }

    public void assertEquals(ETP etp, ETP_DTO etpDTO) {
        assertEquals(etp.id(), etpDTO.getId());
        assertEquals(etp.title(), etpDTO.getTitle());
        assertEquals(etp.target(), etpDTO.getTarget());
        assertEquals(etp.distanceLearningBeginDate(), etpDTO.getDistanceLearningBeginDate());
        assertEquals(etp.distanceLearningEndDate(), etpDTO.getDistanceLearningEndDate());
        assertEquals(etp.fullTimeLearningBeginDate(), etpDTO.getFullTimeLearningBeginDate());
        assertEquals(etp.fullTimeLearningEndDate(), etpDTO.getFullTimeLearningEndDate());
        assertEquals(etpMock.getFullEducationModule().id(), etpDTO.getModules().get(0).getId());
        assertEquals(etpMock.getFullEducationModule().name(), etpDTO.getModules().get(0).getName());
        assertEquals(etpMock.getSection().id(), etpDTO.getModules().get(0).getSections().get(0).getId());
        assertEquals(etpMock.getSection().name(), etpDTO.getModules().get(0).getSections().get(0).getName());
        assertEquals(etpMock.getPlan().id(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getId());
        assertEquals(etpMock.getPlan().lectures(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getLectures());
        assertEquals(etpMock.getPlan().practices(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getPractices());
        assertEquals(etpMock.getPlan().independentWorks(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getIndependentWorks());
        assertEquals(etpMock.getPlan().peerReviews(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getPeerReviews());
        assertEquals(etpMock.getPlan().consultations(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getConsultations());
        assertEquals(etpMock.getPlan().credits(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getCredits());
        assertEquals(etpMock.getPlan().others(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getOthers());
        assertEquals(etpMock.getPlan().totalHours(), etpDTO.getModules().get(0).getSections().get(0).getPlan().getTotalHours());
    }
}
