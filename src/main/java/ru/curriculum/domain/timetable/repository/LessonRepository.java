package ru.curriculum.domain.timetable.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.curriculum.domain.timetable.entity.Lesson;

import java.time.LocalDate;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    @Query(value = "" +
            "SELECT " +
            "  l.* " +
            "FROM lesson l " +
            "  JOIN school_day sd ON l.school_day_id = sd.id " +
            "WHERE sd.timetable_id <> :timetable_id AND sd.date = :d AND l.time = :t AND l.teacher_id = :teacher_id " +
            "LIMIT 1;",
            nativeQuery = true
    )
    Lesson findLessonForTeacherOnDate(
            @Param("teacher_id") Integer teacherId,
            @Param("timetable_id") Integer timetableId,
            @Param("d") LocalDate date,
            @Param("t") String time
    );
}
