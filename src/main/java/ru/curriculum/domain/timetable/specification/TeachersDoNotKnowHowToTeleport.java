package ru.curriculum.domain.timetable.specification;

import ru.curriculum.domain.timetable.entity.Timetable;

public class TeachersDoNotKnowHowToTeleport extends CompositeSpecification<Timetable> {

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Timetable timetable) {
        //TODO: check and form warning
        return null;
    }
}
