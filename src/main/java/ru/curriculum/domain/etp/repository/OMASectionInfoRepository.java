package ru.curriculum.domain.etp.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASectionInfo;

import java.util.List;

public interface OMASectionInfoRepository extends PagingAndSortingRepository<OMASectionInfo, Integer> {

    List<OMASectionInfo> findAllByOrderById();
}
