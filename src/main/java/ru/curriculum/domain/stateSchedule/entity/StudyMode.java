package ru.curriculum.domain.stateSchedule.entity;

import javax.persistence.*;

/**
 * Форма обучения - очная, заочная и пр
 */
@Entity
@Table(name = "study_mode")
public class StudyMode {

    @Id
    private String id;

    @Column(insertable = false, updatable = false)
    private String name;
}
