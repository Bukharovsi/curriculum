package ru.curriculum.domain.timetable.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "school_day")
@Getter
@Accessors(fluent = true)
public class SchoolDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    @OneToMany(
            mappedBy = "schoolDay",
            targetEntity = Lesson.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Lesson> lessons;

    @Setter
    @ManyToOne
    @JoinColumn(name = "timetable_id")
    private Timetable timetable;

    public SchoolDay() {
        this.lessons = new HashSet<>();
    }

    public SchoolDay(LocalDate date, Set<Lesson> lessons) {
        this.date = date;
        this.lessons = lessons;
    }

    public SchoolDay(LocalDate date) {
        this();
        this.date = date;
    }

    public SchoolDay(Integer id, LocalDate date, Set<Lesson> lessons) {
        this.id = id;
        this.date = date;
        this.addLessons(lessons);
    }

    private void addLessons(Set<Lesson> lessons) {
        lessons.forEach(lesson -> lesson.schoolDay(this));
        this.lessons = lessons;
    }
}
