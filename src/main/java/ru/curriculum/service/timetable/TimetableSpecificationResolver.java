package ru.curriculum.service.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.timetable.specification.ISpecification;
import ru.curriculum.domain.timetable.specification.builder.ITimetableSpecificationBuilder;
import ru.curriculum.service.timetable.dto.WeeklyTimetableDto;

@Component
public class TimetableSpecificationResolver {
    private ITimetableSpecificationBuilder builder;

    @Autowired
    public TimetableSpecificationResolver(ITimetableSpecificationBuilder builder) {
        this.builder = builder;
    }

    ISpecification getSpecification(WeeklyTimetableDto dto) {
        return dto.isIgnoreWarnings() ? builder.buildSpecificationIgnoreWarnings() : builder.buildSpecification();
    }
}
