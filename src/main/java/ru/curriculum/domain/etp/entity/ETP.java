package ru.curriculum.domain.etp.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.educationActivity.EAModule;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMAModule;
import ru.curriculum.domain.etp.entity.financingSource.FinancingSource;
import ru.curriculum.domain.etp.entity.organizationMethodicalActivity.OMAModule;
import ru.curriculum.domain.timetable.entity.Timetable;
import ru.curriculum.service.etp.statusManager.ETPStatus;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static ru.curriculum.service.etp.statusManager.ETPStatus.DRAFT;

/*
 * ETP - education thematic plan (УПТ - учебно-тематический план)
 */
@Entity
@Table(name = "etp")
@Getter
@Setter
@Accessors(fluent = true)
public class ETP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String target;

    /** Сроки проведения от ...*/
    private Date beginDate;

    /** Сроки проведения до ... */
    private Date endDate;

    /** Дата начала дистанционного обучения */
    private Date distanceLearningBeginDate;

    /** Дата окончания дистанционного обучения */
    private Date distanceLearningEndDate;

    /** Дата начала очного обучения */
    private Date fullTimeLearningBeginDate;

    /** Дата окончания очного обучения */
    private Date fullTimeLearningEndDate;

    private Integer lernerCount;

    private Integer schoolDaysCount;

    private Integer stateProgramId;

    @Enumerated(EnumType.STRING)
    private FinancingSource financingSource;

    @Embedded
    private VolumeInHours volumeInHours;

    @Enumerated(EnumType.STRING)
    private ETPStatus status;

    @OneToOne(
            mappedBy = "createdFrom",
            targetEntity = Timetable.class,
            fetch = FetchType.EAGER)
    private Timetable timetable;

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
        this.status = DRAFT;
        this.volumeInHours = new VolumeInHours();
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
            Integer id,
            String title,
            String target,
            Date beginDate,
            Date endDate,
            Date distanceLearningBeginDate,
            Date distanceLearningEndDate,
            Date fullTimeLearningBeginDate,
            Date fullTimeLearningEndDate,
            ETPStatus status,
            Integer lernerCount,
            Integer schoolDaysCount,
            FinancingSource financingSource,
            VolumeInHours volumeInHours,
            Set<EAModule> eaModules,
            Set<EMAModule> emaModules,
            Set<OMAModule> omaModules
    ) {
        this.id = id;
        this.title = title;
        this.target = target;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.distanceLearningBeginDate = distanceLearningBeginDate;
        this.distanceLearningEndDate = distanceLearningEndDate;
        this.fullTimeLearningBeginDate = fullTimeLearningBeginDate;
        this.fullTimeLearningEndDate = fullTimeLearningEndDate;
        this.lernerCount = lernerCount;
        this.schoolDaysCount = schoolDaysCount;
        this.financingSource = financingSource;
        this.status = null != status ? status : DRAFT;
        this.volumeInHours = volumeInHours;
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
