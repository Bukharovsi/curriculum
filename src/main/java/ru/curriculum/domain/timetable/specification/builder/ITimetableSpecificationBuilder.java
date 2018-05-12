package ru.curriculum.domain.timetable.specification.builder;

import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.specification.ISpecification;


public interface ITimetableSpecificationBuilder {

    ISpecification<Timetable> buildSpecification();

    ISpecification<Timetable> buildSpecificationIgnoreWarnings();
}
