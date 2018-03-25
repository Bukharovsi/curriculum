package ru.curriculum.domain.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.curriculum.domain.timetable.entity.Timetable;

import javax.transaction.Transactional;

@Transactional
public interface TimetableRepository extends CrudRepository<Timetable, Integer> {
}
