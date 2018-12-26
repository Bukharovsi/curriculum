package ru.curriculum.domain.etp.entity.organizationMethodicalActivity;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;

import javax.persistence.*;


@Entity
@Table(name = "organization_methodical_module")
@Getter @Setter
@Accessors(fluent = true)
public class OMAModule {

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
    @JoinColumn(name = "etp_id")
    private ETP etp;

    private Integer number = 0;

    public OMAModule() {
        this.plan = new Plan();
    }

    public OMAModule(String name, Plan plan) {
        this.name = name;
        this.plan = plan;
    }

    public OMAModule(Integer id, String name, Plan plan, Integer number) {
        this(name, plan);
        this.id = id;
        this.number = number;
    }
}
