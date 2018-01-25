package ru.curriculum.domain.admin.curator.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.curriculum.domain.admin.curator.entity.Curator;

import java.util.List;

public interface CuratorRepository extends PagingAndSortingRepository<Curator, Integer> {

    Curator findByLogin(String login);

    List<Curator> findAllByTeacherIsNull();
}
