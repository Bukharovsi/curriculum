package ru.curriculum.domain.timetable.specification;

import org.apache.commons.collections4.CollectionUtils;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonRepository;
import ru.curriculum.service.timetable.dto.LessonDto;

import java.time.format.DateTimeFormatter;
import java.util.*;
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
        ResultOfApplySpecification result = new ResultOfApplySpecification();
        for (SchoolDay day : timetable.schoolDays()) {
            List<Lesson> lessons = excludeLessonsWithoutTeachersAndAddresses(day.lessons());
            if(0 < lessons.size()) {
                List<Lesson> anotherLessons = lessonRepository.findLessonsOnDateWithTeacher(
                        timetable.id(), day.date()
                );
                lessons.addAll(anotherLessons);
                checkIfTeachersHaveSequenceLessonsInDifferentBuildings(
                        lessons, timetable, result
                );
            }
        }
        return result;
    }

    private List<Lesson> excludeLessonsWithoutTeachersAndAddresses(Set<Lesson> lessons) {
        return lessons.stream()
                .filter(l -> 0 != l.teachers().size() && null != l.address() && !l.address().equals(""))
                .collect(Collectors.toList());
    }

    private void checkIfTeachersHaveSequenceLessonsInDifferentBuildings(
            List<Lesson> lessonsNeedToCheck,
            Timetable timetable,
            ResultOfApplySpecification result
    ) {
        Map<String, List<Lesson>> lessonMap = groupLessonsByTime(lessonsNeedToCheck);
        for (int i = 0; i + 1 < timeList.size(); i++) {
            String curTime = timeList.get(i);
            String nextTime = timeList.get(i + 1);
            if(lessonMap.containsKey(curTime) && lessonMap.containsKey(nextTime)) {
                for (Lesson curLesson : lessonMap.get(curTime)) {
                    for (Lesson nextLesson : lessonMap.get(nextTime)) {
                        Collection<Teacher> teachers = findTeachersInDifferentBuildings(
                                curLesson, nextLesson, timetable
                        );
                        createWarnings(curLesson, nextLesson, teachers, result);
                    }
                }
            }
        }
    }

    private Map<String, List<Lesson>> groupLessonsByTime(List<Lesson> lessons) {
        return lessons.stream().collect(Collectors.groupingBy(Lesson::time));
    }

    private Collection<Teacher> findTeachersInDifferentBuildings(
            Lesson curLesson,
            Lesson nextLesson,
            Timetable timetable
    ) {
        boolean needToFindIntersection = isShortBreak(curLesson, nextLesson) &&
                anyLessonBelongToTimetable(curLesson, nextLesson, timetable) &&
                isTeacherInDifferentBuildings(curLesson, nextLesson);

        return needToFindIntersection ?
                CollectionUtils.intersection(curLesson.teachers(), nextLesson.teachers()) :
                new ArrayList<>();
    }

    private boolean isShortBreak(Lesson curLesson, Lesson nextLesson) {
        return (curLesson.time().equals("09:00-10:30") && nextLesson.time().equals("10:40-12:10")) ||
                (curLesson.time().equals("13:00-14:30") && nextLesson.time().equals("14:40-16:10")) ||
                (curLesson.time().equals("14:40-16:10") && nextLesson.time().equals("16:20-17:50"));
    }

    private boolean anyLessonBelongToTimetable(Lesson curLesson, Lesson nextLesson, Timetable timetable) {
        return nextLesson.schoolDay().timetable().equals(timetable) ||
                curLesson.schoolDay().timetable().equals(timetable);
    }

    private boolean isTeacherInDifferentBuildings(Lesson curLesson, Lesson nextLesson) {
        return !curLesson.address().equals(nextLesson.address());
    }

    private void createWarnings(
            Lesson curLesson,
            Lesson nextLesson,
            Collection<Teacher> teachers,
            ResultOfApplySpecification resultOfApplySpecification
    ) {
        for (Teacher teacher : teachers) {
            resultOfApplySpecification.addWarning(
                    createMessage(curLesson, nextLesson, teacher)
            );
        }
    }

    private String createMessage(Lesson curLesson, Lesson nextLesson, Teacher teacher) {
        return String.format(
                "%s %s ведет два занятия подряд в разных зданиях: \"%s %s\" и \"%s %s\"",
                curLesson.schoolDay().date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                teacher.fullName(),
                curLesson.time(),
                curLesson.address(),
                nextLesson.time(),
                nextLesson.address()
        );
    }
}
