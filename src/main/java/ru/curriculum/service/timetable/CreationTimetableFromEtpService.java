package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.timetable.dto.LessonDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Component
public class CreationTimetableFromEtpService {
    @Autowired
    private ETPRepository etpRepository;

    public Timetable makeTimetable(ETPDto etpDto) {
        LocalDate beginDate = toLocalDate(etpDto.getFullTimeLearningBeginDate());
        LocalDate endDate = toLocalDate(etpDto.getFullTimeLearningEndDate());

        Timetable timetable = new Timetable(
                beginDate,
                endDate,
                etpDto.getTitle(),
                generateSchoolDays(beginDate, endDate),
                etpRepository.findOne(etpDto.getId())
        );

        return timetable;
    }

    private Set<SchoolDay> generateSchoolDays(LocalDate beginDate, LocalDate endDate) {
        return Stream
                .iterate(beginDate, date -> date.plusDays(1))
                .filter(date -> isSchoolDay(date))
                .limit(ChronoUnit.DAYS.between(beginDate, endDate))
                .map(date -> new SchoolDay(date, generateLessons()))
                .collect(toSet());
    }

    private Set<Lesson> generateLessons() {
        return LessonDto.timesList
                .stream()
                .map(time -> Lesson.builder().time(time).build())
                .collect(toSet());
    }

    private boolean isSchoolDay(LocalDate date) {
        return !DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK)).equals(DayOfWeek.SUNDAY);
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
