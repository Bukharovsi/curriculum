package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.timetable.entity.Timetable;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Getter
@Setter
public class TimetableDto {
    private Integer id;
    private LocalDateTime beginDate;
    private LocalDateTime endDate;
    private String theme;
    private List<LessonDto> lessons;

    public TimetableDto(Timetable timetable) {
        this.id = timetable.id();
        this.beginDate = timetable.beginDate();
        this.endDate = timetable.endDate();
        this.theme = timetable.theme();
        this.lessons = timetable.lessons()
                .stream()
                .map(LessonDto::new)
                .collect(toList());
    }
}
