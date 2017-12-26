package ru.curriculum.domain.etp.entity.educationActivityModule;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.service.etp.dto.EASectionDTO;

import javax.persistence.*;

/*
 * EASection - educational activity section.
 * Раздел модуля УТП "Учебная деятельность". Характеризуется планом.
 */
@Entity
@Table(name = "education_activity_section")
@Getter
@Accessors(fluent = true)
public class EASection {
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
    @Setter
    @ManyToOne
    @JoinColumn(name = "education_activity_module_id")
    private EAModule eaModule;

    public EASection() {
    }

    public EASection(String name, Plan plan) {
        this.name = name;
        this.plan = plan;
    }

    public EASection(Integer id, String name, Plan plan) {
        this(name, plan);
        this.id = id;
    }

    public EASection(EASectionDTO sectionDTO) {
        this(
                sectionDTO.getName(),
                new Plan(sectionDTO.getPlan()));
        this.id = sectionDTO.getId();
    }
}
