package ru.curriculum.domain.timetable.specification;

import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonRepository;
import ru.curriculum.service.timetable.dto.LessonDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class TeachersDoNotKnowHowToTeleportSpecification extends CompositeSpecification<Timetable> {

    private LessonRepository lessonRepository;

    public TeachersDoNotKnowHowToTeleportSpecification(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Timetable timetable) {
        ResultOfApplySpecification resultOfApplySpecification = new ResultOfApplySpecification();
        for (SchoolDay day : timetable.schoolDays()) {
            List<Lesson> lessons = lessonRepository.findLessonsOnDateWithTeacher(timetable.id(), day.date());
            lessons.addAll(day.lessons());
//            checkIfTeachersHaveSequenceLessonsInDifferentBuildings(resultOfApplySpecification, lessons);
            test(resultOfApplySpecification, lessons);
        }
        return resultOfApplySpecification;
    }

    private void checkIfTeachersHaveSequenceLessonsInDifferentBuildings(
            ResultOfApplySpecification resultOfApplySpecification,
            List<Lesson> lessonsNeedToCheck
    ) {
//        List<Lesson> lessons = sortByTimeAndTeacher(lessonsNeedToCheck);
        List<Lesson> lessons = null;
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

    //TODO: make pretty
    private void test(
            ResultOfApplySpecification resultOfApplySpecification,
            List<Lesson> lessonsNeedToCheck
    ) {
        List<String> timeList = LessonDto.timesList;
        Map<String, List<Lesson>> lessonMap = sortByTimeAndTeacher(lessonsNeedToCheck);
        for (int i = 0; i < timeList.size(); i++) {
            if(i + 1 >= timeList.size())  {
                return;
            }
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

    /**
     * Exclude lessons without teacher and address then sort by time, teacher.id
     */
    private Map<String, List<Lesson>> sortByTimeAndTeacher(List<Lesson> lessons) {
        Map<String, List<Lesson>> lesson = lessons
                .stream()
                .filter(l -> null != l.teacher() && null != l.address() && !l.address().isEmpty())
                .collect(Collectors.groupingBy(Lesson::time));

        return lesson;
//        return lessons
//                .stream()
//                .filter(l -> null != l.teacher() && null != l.address() && !l.address().isEmpty())
//                .sorted(Comparator
//                        .comparing((Lesson l) -> l.time())
//                        .thenComparing(l -> l.teacher().id()))
//                .collect(toList());
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
