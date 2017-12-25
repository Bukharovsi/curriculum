package ru.curriculum.domain.etp.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import ru.curriculum.service.etp.dto.ETP_DTO;
import ru.curriculum.service.etp.dto.EducationActivityModuleDTO;

import javax.persistence.*;
import java.util.*;

import static java.util.stream.Collectors.toSet;

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
    private Integer id;
    private String title;
    private String target;
    private Date distanceLearningBeginDate;
    private Date distanceLearningEndDate;
    private Date fullTimeLearningBeginDate;
    private Date fullTimeLearningEndDate;
    //    private OrganizationalAndMethodicalActivity organizationalAndMethodicalActivity;
//    private EducationMethodicalActivity educationMethodicalActivity;
    @OneToMany(
            mappedBy = "etp",
            targetEntity = EducationActivityModule.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EducationActivityModule> educationActivityModules;

    public ETP() {
        this.educationActivityModules = new HashSet<>();
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

    public ETP(ETP_DTO etpDTO) {
        this(
                etpDTO.getTitle(),
                etpDTO.getTarget(),
                etpDTO.getDistanceLearningBeginDate(),
                etpDTO.getDistanceLearningEndDate(),
                etpDTO.getFullTimeLearningBeginDate(),
                etpDTO.getFullTimeLearningEndDate());
        this.id = etpDTO.getId();
        this.educationActivityModules = bindWithThisModule(etpDTO.getModules());
    }

    public Set<EducationActivityModule> bindWithThisModule(
            @NonNull Collection<EducationActivityModuleDTO> moduleDTOs
    ) {
        Set<EducationActivityModule> modules = moduleDTOs
                .stream()
                .map(EducationActivityModule::new)
                .collect(toSet());
        modules.forEach(module -> module.etp(this));

        return modules;
    }

    public void addModule(@NonNull EducationActivityModule module) {
        module.etp(this);
        this.educationActivityModules.add(module);
    }
}
