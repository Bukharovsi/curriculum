package ru.curriculum.domain.admin.domain.etp;

import boot.IntegrationBoot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EducationMethodicalSectionInfo;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OrganizationallyMethodicalSectionInfo;
import ru.curriculum.domain.etp.repository.EducationMethodicalSectionInfoRepository;
import ru.curriculum.domain.etp.repository.OrganizationallyMethodicalSectionInfoRepository;

import java.util.List;

public class EducationMethodicalSectionInfoRepositoryTest extends IntegrationBoot {
    @Autowired
    private EducationMethodicalSectionInfoRepository educationMethodicalSectionInfoRepository;
    @Autowired
    private OrganizationallyMethodicalSectionInfoRepository organizationallyMethodicalSectionInfoRepository;

    @Test
    public void findAllEducationMethodicalInfo() {
        List<EducationMethodicalSectionInfo> info = (List<EducationMethodicalSectionInfo>) educationMethodicalSectionInfoRepository.findAll();

        assertEquals(7, info.size());
    }

    @Test
    public void findAllOrganizationallyMethodicalInfo() {
        List<OrganizationallyMethodicalSectionInfo> info = (List<OrganizationallyMethodicalSectionInfo>) organizationallyMethodicalSectionInfoRepository.findAll();

        assertEquals(5, info.size());
    }
}
