package ru.curriculum.domain.timetable.specification.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonRepository;
import ru.curriculum.domain.timetable.specification.ISpecification;
import ru.curriculum.domain.timetable.specification.TeachersDoNotKnowHowToTeleport;
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
        return new TeachersNoNeedMoneySpecification(teacherRepository)
                .and(new TeachersCannotBeCloneSpecification(lessonRepository))
                .and(new TeachersDoNotKnowHowToTeleport(lessonRepository));
    }
}
