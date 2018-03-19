package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.service.teacher.dto.TeacherDto;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class LessonDto {
    private Integer id;
    private String theme;
    private LocalDate date;
    private LocalTime time;
    private Integer lernerCount;
    private TeacherDto teacher;
    private String address;
    private Integer audienceNumber;
    private LessonFormDto lessonForm;

    public LessonDto(Lesson lesson) {
        this.id = lesson.id();
        this.theme = lesson.theme();
        this.date = lesson.date();
        this.time = lesson.time();
        this.lernerCount = lesson.lernerCount();
        this.teacher = new TeacherDto(lesson.teacher());
        this.address = lesson.address();
        this.audienceNumber = lesson.audienceNumber();
        this.lessonForm = new LessonFormDto(lesson.lessonForm());
    }
}
