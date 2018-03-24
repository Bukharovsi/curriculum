package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.timetable.entity.LessonForm;

@Getter
@Setter
public class LessonFormDto {
    private String code;
    private String name;

    public LessonFormDto(LessonForm lessonForm) {
        this.code = lessonForm.code();
        this.name = lessonForm.name();
    }
}
