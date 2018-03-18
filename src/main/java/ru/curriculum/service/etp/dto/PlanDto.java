package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.service.teacher.dto.TeacherDto;

@Getter
@Setter
public class PlanDto {
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
    private Integer lernerCount = 0;
    private Integer groupCount = 0;
    private Integer conditionalPagesCount = 0;
    private TeacherDto teacher;
    private Integer teacherId;

    public PlanDto() {}

    public PlanDto(Plan plan) {
        this.id = plan.id();
        this.lectures = plan.lectures();
        this.practices = plan.practices();
        this.independentWorks = plan.independentWorks();
        this.consultations = plan.consultations();
        this.peerReviews = plan.peerReviews();
        this.credits = plan.credits();
        this.others = plan.others();
        this.standard = plan.standard();
        this.totalHours = plan.totalHours();
        this.lernerCount = plan.lernerCount();
        this.groupCount = plan.groupCount();
        this.conditionalPagesCount = plan.conditionalPagesCount();
        this.teacher = null != plan.teacher() ? new TeacherDto(plan.teacher()) : null;
        this.teacherId = null != plan.teacher() ? plan.teacher().id() : null;
    }

    public boolean hasTeacher() {
        return null != teacherId;
    }
}
