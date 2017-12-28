package ru.curriculum.domain.etp.entity.educationMethodicalSection;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;

import javax.persistence.*;

/*
 * EMASection - Educational Methodical Section.
 * Модуль учебно-методической деятельности.
 */
@Entity
@Table(name = "education_methodical_section")
@Getter
@Accessors(fluent = true)
public class EMASection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = EMSectionInfo.class)
    private EMSectionInfo info;
    @ManyToOne(
            targetEntity = Plan.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "etp_plan_id")
    private Plan plan;
    @ManyToOne
    @JoinColumn(name = "etp_id")
    @Setter
    private ETP etp;

    public EMASection() {
    }

    public EMASection(EMSectionInfo info, Plan plan) {
        this.info = info;
        this.plan = plan;
    }

    public EMASection(
            Integer id,
            EMSectionInfo info,
            Plan plan
    ) {
        this(info, plan);
        this.id = id;
    }
}
