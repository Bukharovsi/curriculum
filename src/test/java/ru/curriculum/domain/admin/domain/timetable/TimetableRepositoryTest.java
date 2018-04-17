package ru.curriculum.domain.admin.domain.timetable;

import boot.IntegrationBoot;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.financingSource.FinancingSource;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
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

    @Autowired
    private ETPRepository etpRepository;

    @After
    public void tearDown() {
        timetableRepository.deleteAll();
        etpRepository.deleteAll();
    }

    @Test
    public void saveTimetableEntity_afterSaveTimetableMustBeEquals() {
        Timetable timetable = getTimetable();

        Timetable savedTimetable = timetableRepository.save(timetable);

        assertEquals(timetable.beginDate(), savedTimetable.beginDate());
        assertEquals(timetable.endDate(), savedTimetable.endDate());
        assertEquals(timetable.theme(), savedTimetable.theme());
        assertEquals(timetable.schoolDays().size(), savedTimetable.schoolDays().size());
    }

    private Timetable getTimetable() {
        ETP etp = new ETP("theme", "target", new Date(), new Date(), new Date(), new Date(), FinancingSource.BUDGET);
        ETP savedEtp = etpRepository.save(etp);

        Set<Lesson> lessons = new HashSet<Lesson>(){
            { add(
                    Lesson.builder()
                            .time("14:40-16:10")
                            .theme("Тема занятия")
                            .lernerCount(20)
                            .lessonForm(lessonFormRepository.findAll().iterator().next())
                            .address("ул. Пушкина")
                            .audienceNumber(23)
                            .build()
            ); }
            { add(
                    Lesson.builder()
                            .time("14:40-16:10")
                            .theme("Тема занятия")
                            .lernerCount(20)
                            .lessonForm(lessonFormRepository.findAll().iterator().next())
                            .address("ул. Пушкина")
                            .audienceNumber(23)
                            .build()
            ); }
        };

        Set<SchoolDay> schoolDays = new HashSet<>();


        Timetable timetable = new Timetable(
                LocalDate.now(),
                LocalDate.now().plusDays(1),
                "Тема обучения",
                schoolDays,
                savedEtp
        );

        return timetable;
    }
}
