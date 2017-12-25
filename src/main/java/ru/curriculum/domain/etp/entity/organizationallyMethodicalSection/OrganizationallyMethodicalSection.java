package ru.curriculum.domain.etp.entity.organizationallyMethodicalSection;

import lombok.Getter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;

import javax.persistence.*;

@Entity
@Table(name = "organizationally_methodical_section")
@Getter
@Accessors(fluent = true)
public class OrganizationallyMethodicalSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = OrganizationallyMethodicalSectionInfo.class)
    private OrganizationallyMethodicalSectionInfo info;
    @ManyToOne(
            targetEntity = Plan.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "etp_plan_id")
    private Plan plan;
    @ManyToOne
    @JoinColumn(name = "etp_id")
    private ETP etp;

    public OrganizationallyMethodicalSection() {
    }
}
