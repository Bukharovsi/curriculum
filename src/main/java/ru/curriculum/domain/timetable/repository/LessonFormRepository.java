package ru.curriculum.domain.timetable.repository;

import org.springframework.data.repository.CrudRepository;
import ru.curriculum.domain.timetable.entity.LessonForm;

public interface LessonFormRepository extends CrudRepository<LessonForm, Integer> {
}
