package ru.curriculum.domain.admin.presentation;

import boot.IntegrationBoot;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.repository.EMASectionInfoRepository;
import ru.curriculum.domain.etp.repository.OMASectionInfoRepository;
import ru.curriculum.presentation.ETP_DTOFactory;
import ru.curriculum.service.etp.dto.ETP_DTO;

public class ETP_DTOFactoryTest extends IntegrationBoot {
    @Autowired
    private ETP_DTOFactory factory;
    @Autowired
    private EMASectionInfoRepository emaSectionInfoRepository;
    @Autowired
    private OMASectionInfoRepository omaSectionInfoRepository;
    private long countOfEducationSections;
    private long countOfOrganizationSections;

    @Before
    public void setUp() {
        countOfEducationSections = emaSectionInfoRepository.count();
        countOfOrganizationSections = omaSectionInfoRepository.count();
    }

    @Test
    public void createEmptyETP_DTO() {
        ETP_DTO dto = factory.createEmptyETP_DTO();

        assertEquals(countOfEducationSections, dto.getEmaSections().size());
        assertEquals(countOfOrganizationSections, dto.getOmaSections().size());
    }
}
