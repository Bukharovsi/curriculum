package ru.curriculum.domain.organization.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * Структурное подразделение университета
 */
@Entity
@Table(name = "division")
@NoArgsConstructor
@Data
@Accessors(fluent = true)
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    public Division(String name) {
        this.name = name;
    }
}
