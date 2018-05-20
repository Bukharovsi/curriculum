package ru.curriculum.domain.timetable.specification.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonRepository;
import ru.curriculum.domain.timetable.specification.ISpecification;
import ru.curriculum.domain.timetable.specification.TeachersDoNotKnowHowToTeleportSpecification;
import ru.curriculum.domain.timetable.specification.TeachersNoNeedMoneySpecification;
import ru.curriculum.domain.timetable.specification.TeachersCannotBeCloneSpecification;

@Component
public class TimetableSpecificationBuilder implements ITimetableSpecificationBuilder {
    private LessonRepository lessonRepository;
    private TeacherRepository teacherRepository;

    @Autowired
    public TimetableSpecificationBuilder(LessonRepository lessonRepository, TeacherRepository teacherRepository) {
        this.lessonRepository = lessonRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public ISpecification<Timetable> buildSpecification() {
        return buildSpecificationIgnoreWarnings()
                .and(new TeachersDoNotKnowHowToTeleportSpecification(lessonRepository));
    }

    @Override
    public ISpecification<Timetable> buildSpecificationIgnoreWarnings() {
        return new TeachersNoNeedMoneySpecification(teacherRepository)
                .and(new TeachersCannotBeCloneSpecification(lessonRepository));
    }
}
