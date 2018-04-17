package ru.curriculum.domain.timetable.entity;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Getter
@Accessors(fluent = true)
public class WeeklyTimetable {
    private Timetable timetable;
    private List<Week> weeks;

    public WeeklyTimetable(Timetable timetable) {
        this.timetable = timetable;
        this.weeks = formWeeks(timetable.schoolDays());
    }

    private List<Week> formWeeks(Set<SchoolDay> schoolDays) {
        List<Week> weeks = new ArrayList<>();
        schoolDays.stream()
                .sorted(Comparator.comparing(SchoolDay::date))
                .forEach(day -> {
                    if(isNewWeek(weeks, day)) {
                        weeks.add(new Week());
                        weeks.get(weeks.size() - 1).addSchoolDay(day);
                    } else {
                        weeks.get(weeks.size() - 1).addSchoolDay(day);
                    }
                });

        return weeks;
    }

    private boolean isNewWeek(List<Week> weeks, SchoolDay day) {
        return 0 == weeks.size() || DayOfWeek.of(day.date().get(ChronoField.DAY_OF_WEEK)).equals(DayOfWeek.MONDAY);
    }
}
