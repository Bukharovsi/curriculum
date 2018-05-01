package ru.curriculum.domain.etp.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.curriculum.domain.teacher.entity.Teacher;

import javax.persistence.*;

/*
 * План в часах по определенным разделам УТП
 */
@Entity
@Table(name = "etp_plan")
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of= {"id"})
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double lectures = 0.0;
    private Double practices = 0.0;
    private Double independentWorks = 0.0;
    private Double consultations = 0.0;
    private Double peerReviews = 0.0;
    private Double credits = 0.0;
    private Double others = 0.0;
    private Double standard = 0.0;
    private Integer lernerCount = 0;
    private Integer groupCount = 0;
    private Integer conditionalPagesCount = 0;
    private Double hoursPerOneListener = 0.0;
    private Double totalHours = 0.0;
    @Setter
    @ManyToOne(targetEntity = Teacher.class)
    private Teacher teacher;
}
