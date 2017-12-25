package ru.curriculum.domain.etp.entity.educationMethodicalSection;

import lombok.Getter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.Plan;

import javax.persistence.*;

@Entity
@Table(name = "education_methodical_section")
@Getter
@Accessors(fluent = true)
public class EducationMethodicalSection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = EducationMethodicalSectionInfo.class)
    private EducationMethodicalSectionInfo info;
    @ManyToOne(
            targetEntity = Plan.class,
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "etp_plan_id")
    private Plan plan;
    @ManyToOne
    @JoinColumn(name = "etp_id")
    private ETP etp;

    public EducationMethodicalSection() {
    }
}
