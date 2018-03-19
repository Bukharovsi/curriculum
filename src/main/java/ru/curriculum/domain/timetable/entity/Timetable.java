package ru.curriculum.domain.timetable.entity;

import lombok.Getter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "timetable")
@Accessors(fluent = true)
@Getter
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime beginDate;

    private LocalDateTime endDate;

    private String theme;

    @OneToMany(
            mappedBy = "timetable",
            targetEntity = Lesson.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Lesson> lessons;

    public Timetable() {
        this.lessons = new HashSet<>();
    }

    public Timetable(LocalDateTime beginDate, LocalDateTime endDate, String theme, Set<Lesson> lessons) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.theme = theme;
        this.addLessons(lessons);
    }

    private void addLessons(Set<Lesson> lessons) {
        lessons.forEach(lesson -> lesson.timetable(this));
        this.lessons = lessons;
    }
}
