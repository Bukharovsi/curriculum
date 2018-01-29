package ru.curriculum.domain.organization.repository;

import org.springframework.data.repository.CrudRepository;
import ru.curriculum.domain.organization.entity.Division;

public interface DivisionRepository extends CrudRepository<Division, Integer> {
}
