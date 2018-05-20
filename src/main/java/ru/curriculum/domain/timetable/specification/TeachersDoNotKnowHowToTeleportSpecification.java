package ru.curriculum.domain.timetable.specification;

import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonRepository;
import ru.curriculum.service.timetable.dto.LessonDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * If break between two sequence lessons is short and these lessons in different buildings
 */
public class TeachersDoNotKnowHowToTeleportSpecification extends CompositeSpecification<Timetable> {
    private LessonRepository lessonRepository;
    private List<String> timeList;

    public TeachersDoNotKnowHowToTeleportSpecification(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
        this.timeList = LessonDto.timesList.stream().sorted().collect(Collectors.toList());
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Timetable timetable) {
        ResultOfApplySpecification resultOfApplySpecification = new ResultOfApplySpecification();
        for (SchoolDay day : timetable.schoolDays()) {
            List<Lesson> currentTimetableLessons = excludeLessonsWithoutTeachersAndAddresses(day.lessons());
            if(0 < currentTimetableLessons.size()) {
                List<Lesson> anotherLessons = lessonRepository.findLessonsOnDateWithTeacher(timetable.id(), day.date());
                currentTimetableLessons.addAll(anotherLessons);
                checkIfTeachersHaveSequenceLessonsInDifferentBuildings(resultOfApplySpecification, currentTimetableLessons);
            }
        }
        return resultOfApplySpecification;
    }

    private List<Lesson> excludeLessonsWithoutTeachersAndAddresses(Set<Lesson> lessons) {
        return lessons.stream()
                .filter(l -> null != l.teacher() && null != l.address() && !l.address().isEmpty())
                .collect(Collectors.toList());
    }

    private void checkIfTeachersHaveSequenceLessonsInDifferentBuildings(
            ResultOfApplySpecification resultOfApplySpecification,
            List<Lesson> lessonsNeedToCheck
    ) {
        Map<String, List<Lesson>> lessonMap = groupLessonsByTime(lessonsNeedToCheck);
        for (int i = 0; i + 1 < timeList.size(); i++) {
            String curTime = timeList.get(i);
            String nextTime = timeList.get(i + 1);
            if(lessonMap.containsKey(curTime) && lessonMap.containsKey(nextTime)) {
                for (Lesson curLesson : lessonMap.get(curTime)) {
                    for (Lesson nextLesson : lessonMap.get(nextTime)) {
                        if(oneTeacherInDifferentBuildings(curLesson, nextLesson)) {
                            resultOfApplySpecification.addWarning(
                                    createWarningMessage(curLesson, nextLesson)
                            );
                        }
                    }
                }
            }
        }
    }

    private Map<String, List<Lesson>> groupLessonsByTime(List<Lesson> lessons) {
        return lessons.stream().collect(Collectors.groupingBy(Lesson::time));
    }

    private boolean oneTeacherInDifferentBuildings(Lesson curLesson, Lesson nextLesson) {
        return null != curLesson.teacher() &&
                null != nextLesson.teacher() &&
                notSameTeacherAtTheSameTime(curLesson, nextLesson) &&
                isShortBreak(curLesson, nextLesson) &&
                isTeacherInDifferentBuildings(curLesson, nextLesson);
    }

    /**
     * If teacher at the same time lead the lesson then ignore this case
     * because it checked in another specification
     */
    private boolean notSameTeacherAtTheSameTime(Lesson curLesson, Lesson nextLesson) {
        return !(curLesson.teacher().equals(nextLesson.teacher()) &&
                curLesson.time().equals(nextLesson.time()));
    }

    /**
     * Break between lesson
     */
    private boolean isShortBreak(Lesson curLesson, Lesson nextLesson) {
        return (curLesson.time().equals("09:00-10:30") && nextLesson.time().equals("10:40-12:10")) ||
                (curLesson.time().equals("13:00-14:30") && nextLesson.time().equals("14:40-16:10")) ||
                (curLesson.time().equals("14:40-16:10") && nextLesson.time().equals("16:20-17:50"));
    }

    private boolean isTeacherInDifferentBuildings(Lesson curLesson, Lesson nextLesson) {
        return curLesson.teacher().equals(nextLesson.teacher()) &&
                !curLesson.address().equals(nextLesson.address());
    }

    private String createWarningMessage(Lesson curLesson, Lesson nextLesson) {
        return String.format(
                "%s %s ведет два занятия подряд в разных зданиях: \"%s %s\" и \"%s %s\"",
                curLesson.schoolDay().date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                curLesson.teacher().fullName(),
                curLesson.time(),
                curLesson.address(),
                nextLesson.time(),
                nextLesson.address()
        );
    }
}
