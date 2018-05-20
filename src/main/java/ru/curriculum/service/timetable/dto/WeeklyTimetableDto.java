package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.timetable.entity.WeeklyTimetable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
@NoArgsConstructor
public class WeeklyTimetableDto {

    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String theme;

    private Integer createFromEtpId;

    private List<WeekDto> weeks;

    private TimetableDtoValidation validation;

    private boolean ignoreWarnings = false;

    public WeeklyTimetableDto(WeeklyTimetable weeklyTimetable) {
        this.id = weeklyTimetable.timetable().id();
        this.beginDate = weeklyTimetable.timetable().beginDate();
        this.endDate = weeklyTimetable.timetable().endDate();
        this.theme = weeklyTimetable.timetable().theme();
        this.createFromEtpId = weeklyTimetable.timetable().createdFrom().id();
        this.weeks = weeklyTimetable.weeks().stream().map(WeekDto::new).collect(toList());
        this.validation = new TimetableDtoValidation();
    }
}
