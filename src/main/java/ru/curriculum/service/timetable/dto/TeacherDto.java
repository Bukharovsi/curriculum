package ru.curriculum.service.timetable.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.curriculum.domain.teacher.entity.Teacher;

@Getter
@Setter
@NoArgsConstructor
public class TeacherDto {
    private Integer teacherId;

    public TeacherDto(Teacher teacher) {
        this.teacherId = teacher.id();
    }
}
