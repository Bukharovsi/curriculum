package ru.curriculum.domain.admin.domain.timetable;

import boot.IntegrationBoot;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.repository.LessonFormRepository;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.TimetableRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class TimetableRepositoryTest extends IntegrationBoot {
    @Autowired
    private LessonFormRepository lessonFormRepository;

    @Autowired
    private TimetableRepository timetableRepository;

    @Test
    public void saveTimetableEntity_afterSaveTimetableMustBeEquals() {
        Timetable timetable = getTimetable();

        Timetable savedTimetable = timetableRepository.save(timetable);

        assertEquals(timetable.beginDate(), savedTimetable.beginDate());
        assertEquals(timetable.endDate(), savedTimetable.endDate());
        assertEquals(timetable.theme(), savedTimetable.theme());
        assertEquals(timetable.lessons().size(), savedTimetable.lessons().size());
    }

    private Timetable getTimetable() {
        Set<Lesson> lessons = new HashSet<Lesson>(){
            { add(
                    Lesson.builder()
                            .date(LocalDate.now())
                            .time(LocalTime.now())
                            .theme("Тема занятия")
                            .lernerCount(20)
                            .lessonForm(lessonFormRepository.findAll().iterator().next())
                            .address("ул. Пушкина")
                            .audienceNumber(23)
                            .build()
            ); }
            { add(
                    Lesson.builder()
                            .date(LocalDate.now())
                            .time(LocalTime.now())
                            .theme("Тема занятия")
                            .lernerCount(20)
                            .lessonForm(lessonFormRepository.findAll().iterator().next())
                            .address("ул. Пушкина")
                            .audienceNumber(23)
                            .build()
            ); }
        };

        Timetable timetable = new Timetable(
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(1),
                "Тема обучения",
                lessons
        );

        return timetable;
    }
}
