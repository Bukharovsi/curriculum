package ru.curriculum.domain.stateSchedule.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Форма реализации - модульная, к примеру.
 */
@Entity
@Table(name = "implementation_form")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImplementationForm {

    @Id
    private String id;

    @Column(insertable = false, updatable = false)
    private String name;
}
