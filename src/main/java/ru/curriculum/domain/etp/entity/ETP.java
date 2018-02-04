package ru.curriculum.domain.etp.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;

import javax.persistence.*;
import java.util.*;

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
    private Integer stateProgramId;

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
            Date fullTimeLearningEndDate
    ) {
        this();
        this.title = title;
        this.target = target;
        this.distanceLearningBeginDate = distanceLearningBeginDate;
        this.distanceLearningEndDate = distanceLearningEndDate;
        this.fullTimeLearningBeginDate = fullTimeLearningBeginDate;
        this.fullTimeLearningEndDate = fullTimeLearningEndDate;
    }

    public ETP(
            String title,
            String target,
            Date distanceLearningBeginDate,
            Date distanceLearningEndDate,
            Date fullTimeLearningBeginDate,
            Date fullTimeLearningEndDate,
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
                fullTimeLearningEndDate
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
