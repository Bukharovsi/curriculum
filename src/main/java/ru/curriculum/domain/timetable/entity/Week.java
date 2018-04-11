package ru.curriculum.domain.timetable.entity;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Accessors(fluent = true)
public class Week {
    private List<SchoolDay> schoolDays;

    public Week() {
        this.schoolDays = new ArrayList<>();
    }

    public void addSchoolDay(SchoolDay schoolDay) {
        if(6 == schoolDays.size()) {
            throw new IndexOutOfBoundsException("Длина учебной недели 6 дней");
        }
        schoolDays.add(schoolDay);
    }
}
