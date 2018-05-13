package ru.curriculum.service.etp.dto;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.service.teacher.dto.TeacherDto;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

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
    private Double hoursPerOneListener = 0.0;
    private Double totalHours = 0.0;
    private Integer lernerCount = 0;
    private Integer groupCount = 0;
    private Integer conditionalPagesCount = 0;
    private List<TeacherDto> teachers;
    private List<Integer> teacherIds;

    public PlanDto() {
        this.teachers = new ArrayList<>();
        this.teacherIds = new ArrayList<>();
    }

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
        this.hoursPerOneListener = plan.hoursPerOneListener();
        this.totalHours = plan.totalHours();
        this.lernerCount = plan.lernerCount();
        this.groupCount = plan.groupCount();
        this.conditionalPagesCount = plan.conditionalPagesCount();
        this.teachers = plan.teachers().stream().map(TeacherDto::new).collect(toList());
        this.teacherIds = plan.teachers().stream().map(t -> t.id()).collect(toList());
    }

    public boolean hasTeacher() {
        return 0 != teacherIds.size();
    }
}
