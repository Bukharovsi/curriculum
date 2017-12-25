package ru.curriculum.domain.etp.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.service.etp.dto.PlanDTO;

import javax.persistence.*;

/*
 * План в часах по определенным разделам УТП
 */
@Entity
@Table(name = "etp_plan")
@Getter
@Accessors(fluent = true)
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
    private Double totalHours = 0.0;
    @ManyToOne(targetEntity = Teacher.class)
    @Setter
    private Teacher teacher;

    public Plan() {
    }

    public Plan(
            Double lectures,
            Double practices,
            Double independentWorks,
            Double consultations,
            Double peerReviews,
            Double credits,
            Double others,
            Double standard,
            Double totalHours
    ) {
        this.lectures = lectures;
        this.practices = practices;
        this.independentWorks = independentWorks;
        this.consultations = consultations;
        this.peerReviews = peerReviews;
        this.credits = credits;
        this.others = others;
        this.standard = standard;
        this.totalHours = totalHours;
    }

    public Plan(PlanDTO plan) {
        this(
                plan.getLectures(),
                plan.getPractices(),
                plan.getIndependentWorks(),
                plan.getConsultations(),
                plan.getPeerReviews(),
                plan.getCredits(),
                plan.getOthers(),
                plan.getStandard(),
                plan.getTotalHours());
        this.id = plan.getId();
        this.teacher = null != plan.getDomainTeacher() ? plan.getDomainTeacher() : null;
    }
}
