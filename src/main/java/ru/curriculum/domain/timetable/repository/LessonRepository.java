package ru.curriculum.domain.timetable.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.curriculum.domain.timetable.entity.Lesson;

import java.time.LocalDate;
import java.util.List;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    @Query(value = "" +
            "SELECT l.* " +
            "  FROM lesson l " +
            "  JOIN school_day sd ON l.school_day_id = sd.id " +
            "  JOIN lesson_teacher lt ON l.id = lt.lesson_id " +
            "   AND lt.teacher_id = :teacher_id " +
            "   AND sd.timetable_id <> :timetable_id " +
            "   AND sd.date = :d " +
            "   AND l.time = :t; ",
            nativeQuery = true
    )
    List<Lesson> findLessonForTeacherOnDate(
            @Param("teacher_id") Integer teacherId,
            @Param("timetable_id") Integer timetableId,
            @Param("d") LocalDate date,
            @Param("t") String time
    );

    @Query(value = "" +
            "WITH lt AS ( " +
            "    SELECT DISTINCT lesson_id FROM lesson_teacher " +
            ") " +
            "SELECT " +
            "  l.* " +
            "FROM school_day sd " +
            "  JOIN lesson l ON sd.id = l.school_day_id AND sd.date = :d AND sd.timetable_id <> :timetable_id" +
            "  JOIN lt ON l.id = lt.lesson_id " +
            "   AND l.address IS NOT NULL AND l.address <> '';",
            nativeQuery = true
    )
    List<Lesson> findLessonsOnDateWithTeacher(
            @Param("timetable_id") Integer timetableId,
            @Param("d") LocalDate date
    );
}
