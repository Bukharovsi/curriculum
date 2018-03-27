package ru.curriculum.domain.timetable.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "lesson_form")
@Getter
@Accessors(fluent = true)
@NoArgsConstructor
public class LessonForm {
    @Id
    private String code;

    private String name;

    public LessonForm(String name) {
        this.name = name;
    }
}
