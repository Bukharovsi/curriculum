package ru.curriculum.domain.timetable.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.curriculum.domain.teacher.entity.Teacher;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "lesson")
@Accessors(fluent = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String theme;

    @Setter
    private String time;

    private Integer lernerCount;

    @ManyToOne(targetEntity = Teacher.class)
    private Teacher teacher;

    @Setter
    private String address;

    @Setter
    private Integer audienceNumber;

    @Setter
    @ManyToOne(targetEntity = LessonForm.class)
    private LessonForm lessonForm;

    @Setter
    @ManyToOne
    @JoinColumn(name = "school_day_id")
    private SchoolDay schoolDay;
}
