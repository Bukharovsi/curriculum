package ru.curriculum.service.timetable.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.domain.timetable.entity.LessonForm;
import ru.curriculum.domain.timetable.entity.SchoolDay;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.domain.timetable.repository.LessonFormRepository;
import ru.curriculum.service.timetable.dto.LessonDto;
import ru.curriculum.service.timetable.dto.SchoolDayDto;
import ru.curriculum.service.timetable.dto.TimetableDto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class TimetableDtoToTimetableConverter {
    @Autowired
    private ETPRepository etpRepository;
    @Autowired
    private LessonFormRepository lessonFormRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    public Timetable convert(TimetableDto timetableDto) {
        Timetable timetable = new Timetable(
                timetableDto.getId(),
                timetableDto.getBeginDate(),
                timetableDto.getEndDate(),
                timetableDto.getTheme(),
                convertSchoolDays(timetableDto.getSchoolDays()),
                etpRepository.findOne(timetableDto.getCreateFromEtpId())
        );
        return timetable;
    }

    private Set<SchoolDay> convertSchoolDays(List<SchoolDayDto> schoolDayDtos) {
        Set<SchoolDay> schoolDays = new HashSet<>();
        schoolDayDtos.forEach(schoolDayDto -> {
            SchoolDay schoolDay = new SchoolDay(
                    schoolDayDto.getId(),
                    schoolDayDto.getDate(),
                    convertLessons(schoolDayDto.getLessons())
            );
            schoolDays.add(schoolDay);
        });
        return schoolDays;
    }

    private Set<Lesson> convertLessons(List<LessonDto> lessonDtos) {
        Set<Lesson> lessons = new HashSet<>();
        lessonDtos.forEach(lessonDto -> {
            LessonForm lessonForm = null;
            if(null != lessonDto.getLessonFormId()) {
                lessonForm = lessonFormRepository.findOne(lessonDto.getLessonFormId());
            }

            Teacher teacher = null;
            if(null != lessonDto.getTeacherId()) {
                teacher = teacherRepository.findOne(lessonDto.getTeacherId());
            }

            Lesson lesson = Lesson.builder()
                    .id(lessonDto.getId())
                    .theme(lessonDto.getTheme())
                    .lessonForm(lessonForm)
                    .time(lessonDto.getTime())
                    .teacher(teacher)
                    .audienceNumber(lessonDto.getAudienceNumber())
                    .lernerCount(lessonDto.getLernerCount())
                    .address(lessonDto.getAddress())
                    .build();
            lessons.add(lesson);
        });
        return lessons;
    }
}
