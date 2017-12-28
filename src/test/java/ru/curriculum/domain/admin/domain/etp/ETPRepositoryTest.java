package ru.curriculum.domain.admin.domain.etp;

import boot.IntegrationBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.etp.entity.educationActivityModule.EAModule;
import ru.curriculum.domain.etp.entity.educationActivityModule.EASection;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMASection;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMSectionInfo;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASection;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASectionInfo;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.etp.repository.EMASectionInfoRepository;
import ru.curriculum.domain.etp.repository.OMASectionInfoRepository;

import java.util.HashSet;
import java.util.Set;

public class ETPRepositoryTest extends IntegrationBoot {
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private EMASectionInfoRepository emaSectionInfoRepository;
    @Autowired
    private OMASectionInfoRepository omaSectionInfoRepository;
    private ETPMock etpMock;

    private Iterable<OMASectionInfo> organizationInfo;
    private Iterable<EMSectionInfo> educationInfo;

    @Before
    public void setUp() {
        this.etpMock = new ETPMock();
        this.organizationInfo = omaSectionInfoRepository.findAll();
        this.educationInfo = emaSectionInfoRepository.findAll();
    }

    @After
    public void tearDown() {
        etpRepository.deleteAll();
    }

    @Test
    public void saveETPEntity_mustBeSavedCorrectly() {
        ETP etp = createFullETPEntity();
        etpRepository.save(etp);

        ETP savedETP = etpRepository.findOne(etp.id());

        assertNotNull(savedETP.id());
        assertNotNull(savedETP.id());
    }

    @Test
    public void saveETPWithEducationActivityModules_savedEntityMustBeContainCorrectETPPlan() {
        ETP etp = etpMock.getETP();
        EAModule module = etpMock.getFullEducationModule();
        etp.addModule(module);
        etpRepository.save(etp);

        ETP savedETP = etpRepository.findOne(etp.id());
        EAModule savedModule = savedETP.eaModules().iterator().next();
        EASection savedSection = savedModule.eaSections().iterator().next();

        assertNotNull(savedSection.id());
        assertNotNull(savedSection.eaModule());
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

    @Test
    public void deleteETPEntity_mustBeDeleteAllChildEntities() {
        ETP etp = createFullETPEntity();
        etpRepository.save(etp);

        assertNotNull("ETP saved correctly", etp.id());
        Integer eptId = etp.id();

        etpRepository.delete(etp);

        assertNull(etpRepository.findOne(eptId));
    }

    private ETP createFullETPEntity() {
        Set<EMASection> methodicalSections = new HashSet<>();
        educationInfo.forEach(info -> methodicalSections.add(new EMASection(info, new Plan())));
        Set<OMASection> organizationSections = new HashSet<>();
        organizationInfo.forEach(info -> organizationSections.add(new OMASection(info, new Plan())));

        return new ETP(
                etpMock.getETP().title(),
                etpMock.getETP().target(),
                etpMock.getETP().distanceLearningBeginDate(),
                etpMock.getETP().distanceLearningEndDate(),
                etpMock.getETP().fullTimeLearningBeginDate(),
                etpMock.getETP().fullTimeLearningEndDate(),
                etpMock.getETP().eaModules(),
                methodicalSections,
                organizationSections);
    }
}
