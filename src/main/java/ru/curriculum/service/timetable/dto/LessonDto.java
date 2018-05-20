package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.timetable.entity.Lesson;
import ru.curriculum.service.teacher.dto.TeacherDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class LessonDto {
    private Integer id;
    private String theme;
    private String time;
    private Integer lernerCount;
    private List<TeacherDto> teachers;
    private List<Integer> teacherIds;
    private String address;
    private Integer audienceNumber;
    private String lessonFormId;
    private String lessonFormName;

    public static final List<String> timesList = new ArrayList<String>() {{
        add("09:00-10:30");
        add("10:40-12:10");
        add("13:00-14:30");
        add("14:40-16:10");
        add("16:20-17:50");
    }};

    public LessonDto() {
        this.teachers = new ArrayList<>();
        this.teacherIds = new ArrayList<>();
    }

    public LessonDto(Lesson lesson) {
        this.id = lesson.id();
        this.theme = lesson.theme();
        this.time = lesson.time();
        this.lernerCount = lesson.lernerCount();
        //TODO: NPE
        this.teachers = lesson.teachers().stream().map(TeacherDto::new).collect(Collectors.toList());
        this.teacherIds = lesson.teachers().stream().map(t -> t.id()).collect(Collectors.toList());
        this.address = lesson.address();
        this.audienceNumber = lesson.audienceNumber();
        if(null != lesson.lessonForm()) {
            this.lessonFormId = lesson.lessonForm().code();
            this.lessonFormName = lesson.lessonForm().name();
        }
    }
}
