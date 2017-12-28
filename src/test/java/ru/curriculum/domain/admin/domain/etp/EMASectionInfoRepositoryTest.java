package ru.curriculum.domain.admin.domain.etp;

import boot.IntegrationBoot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMSectionInfo;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASectionInfo;
import ru.curriculum.domain.etp.repository.EMASectionInfoRepository;
import ru.curriculum.domain.etp.repository.OMASectionInfoRepository;

import java.util.List;

public class EMASectionInfoRepositoryTest extends IntegrationBoot {
    @Autowired
    private EMASectionInfoRepository emaSectionInfoRepository;
    @Autowired
    private OMASectionInfoRepository omaSectionInfoRepository;

    @Test
    public void findAllEducationMethodicalInfo() {
        List<EMSectionInfo> info = (List<EMSectionInfo>) emaSectionInfoRepository.findAll();

        assertEquals(7, info.size());
    }

    @Test
    public void findAllOrganizationallyMethodicalInfo() {
        List<OMASectionInfo> info = (List<OMASectionInfo>) omaSectionInfoRepository.findAll();

        assertEquals(5, info.size());
    }
}
