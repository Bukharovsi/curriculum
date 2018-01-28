package ru.curriculum.domain.admin.domain.etp;

import org.junit.Test;
import ru.curriculum.domain.etp.entity.Plan;

import static org.junit.Assert.*;


public class PlanTest {

    @Test
    public void createNewEtpWithNoArgs_mustCreateWithDefaultValues() {
        Plan plan = new Plan();

        assertNull(plan.id());
        assertEquals(new Double(0.0), plan.lectures());
        assertEquals(new Double(0.0), plan.practices());
        assertEquals(new Double(0.0), plan.independentWorks());
        assertEquals(new Double(0.0), plan.peerReviews());
        assertEquals(new Double(0.0), plan.credits());
        assertEquals(new Double(0.0), plan.consultations());
        assertEquals(new Double(0.0), plan.others());
        assertEquals(new Double(0.0), plan.standard());
        assertEquals(new Double(0.0), plan.totalHours());
        assertEquals(new Integer(0), plan.lernerCount());
        assertEquals(new Integer(0), plan.groupCount());
        assertEquals(new Integer(0), plan.conditionalPagesCount());
        assertNull(plan.teacher());
    }
}
