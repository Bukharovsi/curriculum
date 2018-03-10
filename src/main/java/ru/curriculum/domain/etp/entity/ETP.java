package ru.curriculum.domain.etp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.financingSource.FinancingSource;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/*
 * ETP - education thematic plan (УПТ - учебно-тематический план)
 */
@Entity
@Table(name = "etp")
@Getter
@Accessors(fluent = true)
public class ETP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter
    private Integer id;
    @Setter
    private String title;
    @Setter
    private String target;
    @Setter
    private Date distanceLearningBeginDate;
    @Setter
    private Date distanceLearningEndDate;
    @Setter
    private Date fullTimeLearningBeginDate;
    @Setter
    private Date fullTimeLearningEndDate;
    @Setter
    private Integer lernerCount;
    @Setter
    private Integer schoolDaysCount;
    @Setter
    private Integer stateProgramId;
    @Setter
    @Enumerated(EnumType.STRING)
    private FinancingSource financingSource;

    @OneToMany(
            mappedBy = "etp",
            targetEntity = EAModule.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EAModule> eaModules;
    @OneToMany(
            mappedBy = "etp",
            targetEntity = EMAModule.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EMAModule> emaModules;
    @OneToMany(
            mappedBy = "etp",
            targetEntity = OMAModule.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<OMAModule> omaModules;

    public ETP() {
        this.eaModules = new HashSet<>();
        this.emaModules = new HashSet<>();
        this.omaModules = new HashSet<>();
    }

    public ETP(
            String title,
            String target,
            Date distanceLearningBeginDate,
            Date distanceLearningEndDate,
            Date fullTimeLearningBeginDate,
            Date fullTimeLearningEndDate,
            FinancingSource financingSource
    ) {
        this();
        this.title = title;
        this.target = target;
        this.distanceLearningBeginDate = distanceLearningBeginDate;
        this.distanceLearningEndDate = distanceLearningEndDate;
        this.fullTimeLearningBeginDate = fullTimeLearningBeginDate;
        this.fullTimeLearningEndDate = fullTimeLearningEndDate;
        this.financingSource = financingSource;
    }

    public ETP(
            String title,
            String target,
            Date distanceLearningBeginDate,
            Date distanceLearningEndDate,
            Date fullTimeLearningBeginDate,
            Date fullTimeLearningEndDate,
            FinancingSource financingSource,
            Set<EAModule> eaModules,
            Set<EMAModule> emaModules,
            Set<OMAModule> omaModules
    ) {
        this(
                title,
                target,
                distanceLearningBeginDate,
                distanceLearningEndDate,
                fullTimeLearningBeginDate,
                fullTimeLearningEndDate,
                financingSource
        );
        this.addEAModules(eaModules);
        this.addEMAModules(emaModules);
        this.addOMAModules(omaModules);
    }

    public ETP(
            Integer id,
            String title,
            String target,
            Date distanceLearningBeginDate,
            Date distanceLearningEndDate,
            Date fullTimeLearningBeginDate,
            Date fullTimeLearningEndDate,
            Integer lernerCount,
            Integer schoolDaysCount,
            FinancingSource financingSource,
            Set<EAModule> eaModules,
            Set<EMAModule> emaModules,
            Set<OMAModule> omaModules
    ) {
        this.id = id;
        this.title = title;
        this.target = target;
        this.distanceLearningBeginDate = distanceLearningBeginDate;
        this.distanceLearningEndDate = distanceLearningEndDate;
        this.fullTimeLearningBeginDate = fullTimeLearningBeginDate;
        this.fullTimeLearningEndDate = fullTimeLearningEndDate;
        this.lernerCount = lernerCount;
        this.schoolDaysCount = schoolDaysCount;
        this.financingSource = financingSource;
        this.addEAModules(eaModules);
        this.addEMAModules(emaModules);
        this.addOMAModules(omaModules);
    }

    private void addEAModules(Set<EAModule> eaModules) {
        eaModules.forEach(eaModule -> eaModule.etp(this));
        this.eaModules = eaModules;
    }

    private void addEMAModules(Set<EMAModule> emaModules) {
        emaModules.forEach(emaModule -> emaModule.etp(this));
        this.emaModules = emaModules;
    }

    private void addOMAModules(Set<OMAModule> omaModules) {
        omaModules.forEach(omaModule -> omaModule.etp(this));
        this.omaModules = omaModules;
    }
}
