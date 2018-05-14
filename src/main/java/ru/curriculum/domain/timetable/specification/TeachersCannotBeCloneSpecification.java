package ru.curriculum.domain.timetable.specification;

import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonRepository;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * In one moment teacher hasn't more than one lesson
 */
public class TeachersCannotBeCloneSpecification extends CompositeSpecification<Timetable> {

    private LessonRepository lessonRepository;

    public TeachersCannotBeCloneSpecification(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Timetable timetable) {
        ResultOfApplySpecification resultOfApplySpecification = new ResultOfApplySpecification();
        for (SchoolDay day : timetable.schoolDays()) {
            for (Lesson lesson : day.lessons()) {
                //TODO: пределать
                if(0 < lesson.teachers().size()) {
                    List<Integer> teacherIds = lesson.teachers().stream().map(t -> t.id()).collect(toList());
                    for(int id : teacherIds) {
                        Lesson lessonTeacherAlreadyHas = lessonRepository
                                .findLessonForTeacherOnDate(id, timetable.id(), day.date(), lesson.time());
                        if (null != lessonTeacherAlreadyHas) {
                            resultOfApplySpecification.addError(createErrorMessage(day, lessonTeacherAlreadyHas));
                        }
                    }
                }
            }
        }
        return resultOfApplySpecification;
    }

    private String createErrorMessage(SchoolDay day, Lesson lesson) {
        return String.format("%s в %s преподователь %s одновременно ведет две пары",
                day.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                lesson.time(),
                "Какой-то Уважаемый Преподователь"
        );
    }
}
