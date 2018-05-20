package ru.curriculum.domain.timetable.specification;


import ru.curriculum.domain.etp.entity.financingSource.FinancingSource;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Teacher can't conduct more than four hours in one day
 */
public class TeachersNoNeedMoneySpecification extends CompositeSpecification<Timetable> {

    private TeacherRepository teacherRepository;

    public TeachersNoNeedMoneySpecification(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public ResultOfApplySpecification isSatisfiedBy(Timetable timetable) {
        ResultOfApplySpecification result = new ResultOfApplySpecification();
        if(timetable.createdFrom().financingSource().equals(FinancingSource.BUDGET)) {
            return result;
        }
        for (SchoolDay day : timetable.schoolDays()) {
            List<Teacher> teachers = teacherRepository.findAllHavingLessonOnDate(timetable.id(), day.date());
            for (Lesson lesson : day.lessons()) {
                if(0 < lesson.teachers().size()) {
                    teachers.addAll(lesson.teachers());
                }
            }
            checkIfTeacherHasMoreThan4HoursInOneDay(day, groupTeacher(teachers), result);
        }
        return result;
    }

    private void checkIfTeacherHasMoreThan4HoursInOneDay(
            SchoolDay day,
            Map<Teacher, Long> groupingTeachers,
            ResultOfApplySpecification result
    ) {
        groupingTeachers.forEach((teacher, lessonCount) -> {
            if (4 < lessonCount) {
                result.addError(createError(day, teacher));
            }
        });
    }

    private Map<Teacher, Long> groupTeacher(List<Teacher> teachers) {
        return teachers
                .stream()
                .collect(Collectors.groupingBy(t -> t, Collectors.counting()));
    }

    private String createError(SchoolDay day, Teacher teacher) {
        return String.format("%s преподователь %s работает больше 4 часов",
                day.date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                teacher.fullName()
        );
    }
}
