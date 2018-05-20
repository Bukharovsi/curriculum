package ru.curriculum.domain.timetable.specification;

import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;

import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * In one moment teacher hasn't more than one lesson
 */
public class TeachersCannotBeCloneSpecification extends CompositeSpecification<Timetable> {

    private TeacherRepository teacherRepository;

    public TeachersCannotBeCloneSpecification(TeacherRepository lessonRepository) {
        this.teacherRepository = lessonRepository;
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Timetable timetable) {
        ResultOfApplySpecification result = new ResultOfApplySpecification();
        for (SchoolDay day : timetable.schoolDays()) {
            for (Lesson lesson : day.lessons()) {
                if(0 < lesson.teachers().size()) {
                    List<Integer> teacherIds = lesson.teachers()
                            .stream()
                            .map(t -> t.id())
                            .collect(toList());
                    List<Teacher> teachers = teacherRepository.findAllHavingLessonOnDateAndTime(
                            teacherIds, timetable.id(), day.date(), lesson.time()
                    );
                    createErrors(day, lesson, teachers, result);
                }
            }
        }
        return result;
    }

    private void createErrors(
            SchoolDay day,
            Lesson lesson,
            List<Teacher> teachers,
            ResultOfApplySpecification result
    ) {
        for (Teacher teacher : teachers) {
            result.addError(createMessage(day, lesson, teacher));
        }
    }

    private String createMessage(SchoolDay day, Lesson lesson, Teacher teacher) {
        return String.format(
                "%s в %s преподователь %s одновременно ведет две пары",
                day.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                lesson.time(),
                teacher.fullName()
        );
    }
}
