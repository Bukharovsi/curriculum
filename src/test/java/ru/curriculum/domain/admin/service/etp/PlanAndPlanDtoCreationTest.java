package ru.curriculum.domain.admin.service.etp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.curriculum.domain.admin.domain.etp.ETPMock;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.service.etp.converter.PlanFactory;
import ru.curriculum.service.etp.dto.PlanDto;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlanAndPlanDtoCreationTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private PlanFactory planFactory;

    private ETPMock etpMock;

    @Before
    public void setUp() {
        etpMock = new ETPMock();
        when(teacherRepository.findOne(etpMock.getTeacher().id()))
                .thenReturn(etpMock.getTeacher());
    }

    @Test
    public void createPlan_mustBeCreateCorrectly() {
        PlanDto dto = etpMock.getPlanDTO();
        Plan plan = planFactory.create(dto);

        assertPlan(dto, plan);
    }

    @Test
    public void createPlanDTOFromPlan_mustBeCreateWithEqualValues() {
        Plan plan = etpMock.getPlan();
        PlanDto dto = new PlanDto(plan);

        assertPlan(dto, plan);
    }

    public void assertPlan(PlanDto dto, Plan plan) {
        assertEquals(dto.getId(), plan.id());
        assertEquals(dto.getLectures(), plan.lectures());
        assertEquals(dto.getPractices(), plan.practices());
        assertEquals(dto.getConsultations(), plan.consultations());
        assertEquals(dto.getPeerReviews(), plan.peerReviews());
        assertEquals(dto.getCredits(), plan.credits());
        assertEquals(dto.getOthers(), plan.others());
        assertEquals(dto.getIndependentWorks(), plan.independentWorks());
        assertEquals(dto.getStandard(), plan.standard());
        assertEquals(dto.getTotalHours(), plan.totalHours());
        assertEquals(dto.getLernerCount(), plan.lernerCount());
        assertEquals(dto.getHoursPerOneListener(), plan.hoursPerOneListener());
        assertEquals(dto.getGroupCount(), plan.groupCount());
        assertEquals(dto.getConditionalPagesCount(), plan.conditionalPagesCount());
        assertNotNull(plan.teacher());
    }
}
