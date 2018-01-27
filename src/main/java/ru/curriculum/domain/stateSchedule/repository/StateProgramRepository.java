package ru.curriculum.domain.stateSchedule.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import java.util.Collection;

public interface StateProgramRepository extends PagingAndSortingRepository<StateProgram, Integer> {

    Collection<StateProgram> findByCurator(Curator curator);
}
