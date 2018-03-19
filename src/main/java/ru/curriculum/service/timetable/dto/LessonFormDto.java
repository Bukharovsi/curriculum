package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.timetable.entity.LessonForm;

@Getter
@Setter
public class LessonFormDto {
    private Integer id;
    private String name;

    public LessonFormDto(LessonForm lessonForm) {
        this.id = lessonForm.id();
        this.name = lessonForm.name();
    }
}
