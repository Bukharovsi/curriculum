package ru.curriculum.domain.stateSchedule.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "internship")
@Getter
@Accessors(fluent = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"id", "stateProgram"})
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateStart;

    private Date dateFinish;

    private String address;

    private String theme;

    @Setter
    @ManyToOne
    @JoinColumn(name = "state_program_id")
    private StateProgram stateProgram;
}
