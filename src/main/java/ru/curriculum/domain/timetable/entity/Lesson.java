package ru.curriculum.domain.timetable.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.curriculum.domain.teacher.entity.Teacher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "lesson")
@Accessors(fluent = true)
@Getter
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

    @Singular
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "lesson_teacher",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers;

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

    public Lesson() {
        this.teachers = new HashSet<>();
    }
}
