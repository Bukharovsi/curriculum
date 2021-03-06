package ru.curriculum.domain.etp.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.curriculum.domain.teacher.entity.Teacher;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * План в часах по определенным разделам УТП
 */
@Entity
@Table(name = "etp_plan")
@Getter
@Accessors(fluent = true)
@AllArgsConstructor
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "plan_teacher",
            joinColumns = @JoinColumn(name = "plan_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private Set<Teacher> teachers;

    public Plan() {
        this.teachers = new HashSet<>();
    }
}
