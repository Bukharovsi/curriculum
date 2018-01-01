package ru.curriculum.domain.stateSchedule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Форма реализации - модульная, к примеру.
 */
@Entity
@Table(name = "study_mode")
public class ImplementationForm {

    @Id
    private String id;

    @Column(insertable = false, updatable = false)
    private String name;
}
