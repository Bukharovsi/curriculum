package ru.curriculum.domain.etp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMSectionInfo;

import java.util.List;

public interface EMASectionInfoRepository extends PagingAndSortingRepository<EMSectionInfo, Integer> {

    List<EMSectionInfo> findAllByOrderById();
}
