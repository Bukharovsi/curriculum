package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.service.teacher.dto.TeacherDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class LessonDto {
    private Integer id;
    private String theme;
    private String time;
    private Integer lernerCount;
    private TeacherDto teacher;
    private Integer teacherId;
    private String address;
    private Integer audienceNumber;
    private String lessonFormId;
    private String lessonFormName;

    private final List<String> timesList = new ArrayList<String>() {{
        add("9:00-10:30");
        add("10:40-12:10");
        add("13:00-14:30");
        add("14:00-16:10");
        add("16:20-17:50");
    }};

    public LessonDto(Lesson lesson) {
        this.id = lesson.id();
        this.theme = lesson.theme();
        this.time = lesson.time();
        this.lernerCount = lesson.lernerCount();
        if(null != lesson.teacher()) {
            this.teacher =  new TeacherDto(lesson.teacher());
            this.teacherId = lesson.teacher().id();
        }
        this.address = lesson.address();
        this.audienceNumber = lesson.audienceNumber();
        if(null != lesson.lessonForm()) {
            this.lessonFormId = lesson.lessonForm().code();
            this.lessonFormName = lesson.lessonForm().name();
        }
    }
}
