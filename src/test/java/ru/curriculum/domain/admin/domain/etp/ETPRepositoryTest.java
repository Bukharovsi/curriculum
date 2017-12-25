package ru.curriculum.domain.admin.domain.etp;

import boot.IntegrationBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.educationActivityModule.EducationActivityModule;
import ru.curriculum.domain.etp.entity.educationActivityModule.EducationActivitySection;
import ru.curriculum.domain.etp.repository.ETPRepository;

public class ETPRepositoryTest extends IntegrationBoot {
    @Autowired
    private ETPRepository etpRepository;
    private ETPMock etpMock;

    @Before
    public void setUp() {
        this.etpMock = new ETPMock();
    }

    @After
    public void tearDown() {
        etpRepository.deleteAll();
    }

    @Test
    public void saveETP() {
        ETP etp = etpMock.getETP();
        ETP savedETP = etpRepository.save(etp);

        assertNotNull(savedETP.id());
        assertEquals(etp.title(), savedETP.title());
        assertEquals(etp.target(), savedETP.target());
        assertTrue(savedETP.educationActivityModules().isEmpty());
    }

    @Test
    public void saveETPWithEducationModules_mustBeSaveAllEntity() {
        ETP etp = etpMock.getETP();
        EducationActivityModule module = etpMock.getEducationModule();
        etp.addModule(module);
        etpRepository.save(etp);

        ETP savedETP = etpRepository.findOne(etp.id());
        EducationActivityModule savedModule = savedETP.educationActivityModules().iterator().next();

        assertNotNull(savedModule);
        assertNotNull(savedModule.id());
        assertEquals(module.name(), savedModule.name());
        assertTrue(savedModule.educationActivitySections().isEmpty());
    }

    @Test
    public void saveETPWithEducationModulesWhichContainSectionModuleAndPlan_mustBeSavedAllEntity() {
        ETP etp = etpMock.getETP();
        EducationActivityModule module = etpMock.getFullEducationModule();
        etp.addModule(module);
        etpRepository.save(etp);

        ETP savedETP = etpRepository.findOne(etp.id());
        EducationActivityModule savedModule = savedETP.educationActivityModules().iterator().next();
        EducationActivitySection savedSection = savedModule.educationActivitySections().iterator().next();

        assertNotNull(savedSection.id());
        assertNotNull(savedSection.educationActivityModule());
        assertEquals(etpMock.getSection().name(), savedSection.name());
        assertNotNull(savedSection.plan());
        assertNotNull(savedSection.plan().id());
        assertEquals(etpMock.getPlan().lectures(), savedSection.plan().lectures());
        assertEquals(etpMock.getPlan().practices(), savedSection.plan().practices());
        assertEquals(etpMock.getPlan().consultations(), savedSection.plan().consultations());
        assertEquals(etpMock.getPlan().peerReviews(), savedSection.plan().peerReviews());
        assertEquals(etpMock.getPlan().independentWorks(), savedSection.plan().independentWorks());
        assertEquals(etpMock.getPlan().credits(), savedSection.plan().credits());
        assertEquals(etpMock.getPlan().standard(), savedSection.plan().standard());
        assertEquals(etpMock.getPlan().totalHours(), savedSection.plan().totalHours());
    }
}
