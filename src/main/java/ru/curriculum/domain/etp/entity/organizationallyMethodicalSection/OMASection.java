package ru.curriculum.domain.etp.entity.organizationallyMethodicalSection;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;

import javax.persistence.*;

/*
 * OMASection - Organizationally methodical section.
 * Модуль организационно-методической деятельности.
 */
@Entity
@Table(name = "organizationally_methodical_section")
@Getter
@Accessors(fluent = true)
public class OMASection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = OMASectionInfo.class)
    private OMASectionInfo info;
    @ManyToOne(
            targetEntity = Plan.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "etp_plan_id")
    private Plan plan;
    @Setter
    @ManyToOne
    @JoinColumn(name = "etp_id")
    private ETP etp;

    public OMASection() {
    }

    public OMASection(OMASectionInfo info, Plan plan) {
        this.info = info;
        this.plan = plan;
    }

    public OMASection(
            Integer id,
            OMASectionInfo info,
            Plan plan
    ) {
        this(info, plan);
        this.id = id;
    }
}
