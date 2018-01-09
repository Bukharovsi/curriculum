package ru.curriculum.domain.etp.entity.organizationMethodicalActivity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ISection;
import ru.curriculum.domain.etp.entity.Plan;

import javax.persistence.*;


@Entity
@Table(name = "organization_methodical_section")
@Getter
@Accessors(fluent = true)
public class OMASection implements ISection {
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
    @JoinColumn(name = "organization_methodical_module_id")
    private OMAModule omaModule;

    public OMASection() {
        this.plan = new Plan();
    }

    public OMASection(String name, Plan plan) {
        this.name = name;
        this.plan = plan;
    }
}
