package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.timetable.entity.Timetable;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
@NoArgsConstructor
public class TimetableDto {
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private String theme;

    private Integer createFromEtpId;

    private List<SchoolDayDto> schoolDays;

    public TimetableDto(Timetable timetable) {
        this.id = timetable.id();
        this.beginDate = timetable.beginDate();
        this.endDate = timetable.endDate();
        this.theme = timetable.theme();
        this.createFromEtpId = null != timetable.createdFrom() ? timetable.createdFrom().id() : null;
        this.schoolDays = timetable.schoolDays()
                .stream()
                .map(SchoolDayDto::new)
                .sorted(Comparator.comparing(SchoolDayDto::getDate))
                .collect(toList());
    }
}
