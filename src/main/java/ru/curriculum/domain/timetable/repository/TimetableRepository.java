package ru.curriculum.domain.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.curriculum.domain.timetable.entity.Timetable;

public interface TimetableRepository extends CrudRepository<Timetable, Integer> {
}
