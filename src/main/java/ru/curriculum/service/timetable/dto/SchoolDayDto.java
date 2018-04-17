package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.timetable.entity.SchoolDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;


@Getter
@Setter
public class SchoolDayDto {

    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String dayOfWeek;

    private List<LessonDto> lessons;

    public SchoolDayDto() {
        this.lessons = new ArrayList<>();
    }

    public SchoolDayDto(SchoolDay schoolDay) {
        this.id = schoolDay.id();
        this.date = schoolDay.date();
        this.dayOfWeek = null != schoolDay.date() ? toDisplayFormat(schoolDay.date()) : null;
        this.lessons = schoolDay.lessons()
                .stream()
                .map(LessonDto::new)
                .sorted(Comparator.comparing(LessonDto::getTime))
                .collect(toList());
    }

    private String toDisplayFormat(LocalDate date) {
        String dayOfWeek = DayOfWeek
                .of(date.get(ChronoField.DAY_OF_WEEK))
                .getDisplayName(TextStyle.FULL, new Locale("ru"));

        return dayOfWeek.substring(0, 1).toUpperCase()
                .concat(dayOfWeek.substring(1, dayOfWeek.length()));
    }
}
