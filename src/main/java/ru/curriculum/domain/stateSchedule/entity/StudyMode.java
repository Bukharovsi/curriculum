package ru.curriculum.domain.stateSchedule.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Форма обучения - очная, заочная и пр
 */
@Entity
@Table(name = "study_mode")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudyMode {

    @Id
    private String id;

    @Column(insertable = false, updatable = false)
    private String name;
}
