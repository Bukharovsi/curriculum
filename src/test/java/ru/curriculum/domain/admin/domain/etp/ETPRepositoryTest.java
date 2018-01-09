package ru.curriculum.domain.admin.domain.etp;

import boot.IntegrationBoot;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;
import ru.curriculum.domain.etp.entity.educationActivity.EATopic;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMASection;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMASection;
import ru.curriculum.domain.etp.repository.ETPRepository;

public class ETPRepositoryTest extends IntegrationBoot {
    @Autowired
    private ETPRepository etpRepository;
    private ETPMock etpMock;

    @Before
    public void setUp() {
        etpMock = new ETPMock();
    }

    @Test
    public void saveNewETPEntity_mustBeSavedCorrectly() {
        ETP etp = etpMock.getETP();

        ETP savedETP = etpRepository.save(etp);
        assertNotNull(savedETP.id());

        EAModule eaModule = savedETP.eaModules().iterator().next();
        assertNotNull(eaModule.id());
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

        assertEquals(
                etp.emaModules().iterator().next().sections().size(),
                savedETP.emaModules().iterator().next().sections().size()
        );

        assertEquals(
                etp.omaModules().iterator().next().sections().size(),
                savedETP.omaModules().iterator().next().sections().size()
        );
    }
}
