package ru.curriculum.domain.timetable.specification;

import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonRepository;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class TeachersDoNotKnowHowToTeleport extends CompositeSpecification<Timetable> {

    private LessonRepository lessonRepository;

    public TeachersDoNotKnowHowToTeleport(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Timetable timetable) {
        ResultOfApplySpecification resultOfApplySpecification = new ResultOfApplySpecification();
        for (SchoolDay day : timetable.schoolDays()) {
            List<Lesson> lessons = lessonRepository.findLessonsOnDateWithTeacher(timetable.id(), day.date());
            lessons.addAll(day.lessons());
            checkIfTeachersHaveSequenceLessonsInDifferentBuildings(resultOfApplySpecification, lessons);
        }
        return resultOfApplySpecification;
    }

    private void checkIfTeachersHaveSequenceLessonsInDifferentBuildings(
            ResultOfApplySpecification resultOfApplySpecification,
            List<Lesson> lessons 
    ) {
        lessons.sort(Comparator.comparing(Lesson::time));
        for (int curLesson = 0; curLesson < lessons.size(); curLesson++) {
            int nextLesson = curLesson + 1;
            if(nextLesson < lessons.size()) {
                if(oneTeacherInDifferentBuildings(lessons.get(curLesson), lessons.get(nextLesson))) {
                    resultOfApplySpecification.addWarning(
                            createWarningMessage(lessons.get(curLesson), lessons.get(nextLesson))
                    );
                }
            }
        }
    }

    private boolean oneTeacherInDifferentBuildings(Lesson curLesson, Lesson nextLesson) {
        return null != curLesson.teacher() &&
                null != nextLesson.teacher() &&
                curLesson.teacher().equals(nextLesson.teacher()) &&
                !curLesson.address().equals(nextLesson.address());
    }

    private String createWarningMessage(Lesson curLesson, Lesson nextLesson) {
        return String.format(
                "%s преподователь %s проводит занятия подряд в разных зданиях, в %s и %s",
                curLesson.schoolDay().date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                curLesson.teacher().fullName(),
                curLesson.time(),
                nextLesson.time()
        );
    }
}
