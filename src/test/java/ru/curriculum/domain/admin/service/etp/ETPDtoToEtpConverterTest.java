package ru.curriculum.domain.admin.service.etp;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.service.etp.converter.ETPDtoToEtpConverter;
import ru.curriculum.service.etp.converter.PlanFactory;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.etp.statusManager.ETPStatus;

@RunWith(MockitoJUnitRunner.class)
public class ETPDtoToEtpConverterTest extends Assert {
    @Mock
    private PlanFactory planFactory;
    @InjectMocks
    private ETPDtoToEtpConverter etpDtoToEtpConverter;

    private ETPMock etpMock;

    @Before
    public void setUp() {
        etpMock = new ETPMock();
    }

    @Test
    public void createETP() {
        ETPDto etpDTO = etpMock.getETP_DTO();
        ETP etp = etpDtoToEtpConverter.convert(etpDTO);

        assertEquals(etpDTO.getId(), etp.id());
        assertEquals(etpDTO.getTitle(), etp.title());
        assertEquals(etpDTO.getTarget(), etp.target());
        assertEquals(etpDTO.getDistanceLearningBeginDate(), etp.distanceLearningBeginDate());
        assertEquals(etpDTO.getDistanceLearningEndDate(), etp.distanceLearningEndDate());
        assertEquals(etpDTO.getFullTimeLearningBeginDate(), etp.fullTimeLearningBeginDate());
        assertEquals(etpDTO.getFullTimeLearningEndDate(), etp.fullTimeLearningEndDate());
        assertEquals(ETPStatus.DRAFT, etp.status());
        assertEquals(etpDTO.getLernerCount(), etp.lernerCount());
        assertEquals(etpDTO.getSchoolDaysCount(), etp.schoolDaysCount());
        assertEquals(etpDTO.getFinancingSource(), etp.financingSource());
        assertEquals(etpDTO.getEaModules().size(), etp.eaModules().size());
        assertEquals(etpDTO.getEmaModules().size(), etp.emaModules().size());
        assertEquals(etpDTO.getOmaModules().size(), etp.omaModules().size());
    }
}
