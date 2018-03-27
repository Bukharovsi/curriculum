package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.timetable.entity.Timetable;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Comparator.*;
import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

@Getter
@Setter
@NoArgsConstructor
public class TimetableDto {
    private Integer id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime beginDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime endDate;

    private String theme;

    private Integer createFromEtpId;

    private List<LessonDto> lessons;

    public TimetableDto(Timetable timetable) {
        this.id = timetable.id();
        this.beginDate = timetable.beginDate();
        this.endDate = timetable.endDate();
        this.theme = timetable.theme();
        this.createFromEtpId = null != timetable.createdFrom() ? timetable.createdFrom().id() : null;
        this.lessons = timetable.lessons()
                .stream()
                .map(LessonDto::new)
                .sorted(nullsLast(
                        comparing(LessonDto::getDate, nullsLast(naturalOrder())))
                        .thenComparing(comparing(LessonDto::getId, nullsLast(naturalOrder()))))
                .collect(toList());
    }
}
