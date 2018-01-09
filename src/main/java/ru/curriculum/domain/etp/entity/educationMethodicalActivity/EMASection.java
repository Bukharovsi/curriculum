package ru.curriculum.domain.etp.entity.educationMethodicalActivity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ISection;
import ru.curriculum.domain.etp.entity.Plan;

import javax.persistence.*;


@Entity
@Table(name = "education_methodical_section")
@Getter
@Accessors(fluent = true)
public class EMASection implements ISection {
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
    @JoinColumn(name = "education_methodical_module_id")
    private EMAModule emaModule;

    public EMASection() {
        this.plan = new Plan();
    }

    public EMASection(String name, Plan plan) {
        this.name = name;
        this.plan = plan;
    }
}
