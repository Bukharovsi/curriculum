package ru.curriculum.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface HelloRepository extends PagingAndSortingRepository<Hello, Integer> {
}
