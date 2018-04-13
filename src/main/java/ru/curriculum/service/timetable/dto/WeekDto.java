package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.curriculum.domain.timetable.entity.Week;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Setter
@Getter
@NoArgsConstructor
public class WeekDto {
    private List<SchoolDayDto> schoolDays;

    public WeekDto(Week week) {
        this.schoolDays = week.schoolDays()
                .stream()
                .map(SchoolDayDto::new)
                .collect(toList());
    }
}
