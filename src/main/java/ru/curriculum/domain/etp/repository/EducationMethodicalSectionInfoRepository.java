package ru.curriculum.domain.etp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EducationMethodicalSectionInfo;

public interface EducationMethodicalSectionInfoRepository extends PagingAndSortingRepository<EducationMethodicalSectionInfo, Integer> {
}
