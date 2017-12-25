package ru.curriculum.domain.etp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.service.etp.dto.EducationActivitySectionDTO;

import javax.persistence.*;

@Entity
@Table(name = "education_activity_section")
@Getter
@Accessors(fluent = true)
public class EducationActivitySection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(
            targetEntity = Plan.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "etp_plan_id")
    private Plan plan;
    @ManyToOne
    @JoinColumn(name = "education_activity_module_id")
    @Setter
    private EducationActivityModule educationActivityModule;

    public EducationActivitySection() {
    }

    public EducationActivitySection(String name, Plan plan) {
        this.name = name;
        this.plan = plan;
    }

    public EducationActivitySection(EducationActivitySectionDTO sectionDTO) {
        this(
                sectionDTO.getName(),
                new Plan(sectionDTO.getPlan()));
        this.id = sectionDTO.getId();
    }
}
