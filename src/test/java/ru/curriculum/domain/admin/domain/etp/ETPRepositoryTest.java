package ru.curriculum.domain.admin.domain.etp;

import boot.IntegrationBoot;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;

public class ETPRepositoryTest extends IntegrationBoot {

    @Autowired
    private ETPRepository etpRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    private ETPMock etpMock;

    @Before
    public void setUp() {
        etpMock = new ETPMock();
        Teacher teacher = new Teacher(
                null,
                etpMock.getTeacher().surname(),
                etpMock.getTeacher().firstName(),
                etpMock.getTeacher().patronymic(),
                etpMock.getTeacher().academicDegree(),
                etpMock.getTeacher().patronymic(),
                etpMock.getTeacher().positionHeld(),
                etpMock.getTeacher().type()
        );
        Plan plan = etpMock.getPlan();
        plan.teacher(teacherRepository.save(teacher));
        etpMock.applyNewPlanForModules(plan);
    }

    @After
    public void tearDown() {
        etpRepository.deleteAll();
        teacherRepository.deleteAll();
    }

    @Test
    public void saveNewETPEntity_mustBeSavedCorrectly() {
        ETP etp = etpMock.getETP();

        ETP savedETP = etpRepository.save(etp);
        assertNotNull(savedETP.id());

        EAModule eaModule = savedETP.eaModules().iterator().next();
        assertNotNull(eaModule.id());
        Assert.assertEquals(etp.financingSource(), savedETP.financingSource());
        assertEquals(etp.eaModules().size(), savedETP.eaModules().size());
        assertEquals(etp.emaModules().size(), savedETP.emaModules().size());
        assertEquals(etp.omaModules().size(), savedETP.omaModules().size());

        assertEquals(
                etp.eaModules().iterator().next().sections().size(),
                savedETP.eaModules().iterator().next().sections().size()
        );

        assertEquals(
                etp.eaModules().iterator().next().sections().iterator().next().topics().size(),
                savedETP.eaModules().iterator().next().sections().iterator().next().topics().size()
        );

    }

    @Test
    public void saveETPEntityThenDelete_mustDeletedAllChildEntities() {
        ETP etp = etpMock.getETP();
        Integer id = etpRepository.save(etp).id();

        assertNotNull("ETP created", id);

        etpRepository.delete(id);

        assertNull("ETP deleted", etpRepository.findOne(id));
    }
}
