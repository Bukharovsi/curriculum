package ru.curriculum.domain.etp.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.educationMethodicalSection.EMASection;
import ru.curriculum.domain.etp.entity.organizationallyMethodicalSection.OMASection;
import ru.curriculum.domain.etp.entity.educationActivityModule.EAModule;
import ru.curriculum.service.etp.dto.ETP_DTO;
import ru.curriculum.service.etp.dto.EAModuleDTO;

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
    @OneToMany(
            mappedBy = "etp",
            targetEntity = EAModule.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EAModule> eaModules;
    @OneToMany(
            mappedBy = "etp",
            targetEntity = EMASection.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EMASection> emaSections;
    @OneToMany(
            mappedBy = "etp",
            targetEntity = OMASection.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<OMASection> omaSections;

    public ETP() {
        this.eaModules = new HashSet<>();
        this.emaSections = new HashSet<>();
        this.omaSections = new HashSet<>();
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
            Set<EMASection> emaSections,
            Set<OMASection> omaSections
    ) {
        this.title = title;
        this.target = target;
        this.distanceLearningBeginDate = distanceLearningBeginDate;
        this.distanceLearningEndDate = distanceLearningEndDate;
        this.fullTimeLearningBeginDate = fullTimeLearningBeginDate;
        this.fullTimeLearningEndDate = fullTimeLearningEndDate;
        addEducationActivityModule(eaModules);
        addEducationMethodicalSections(emaSections);
        addOrganizationallyMethodicalSections(omaSections);
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
        this.eaModules = bindWithThisModule(etpDTO.getEaModules());
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
            Set<EMASection> emaSections,
            Set<OMASection> omaSections
    ) {
        this(
                title,
                target,
                distanceLearningBeginDate,
                distanceLearningEndDate,
                fullTimeLearningBeginDate,
                fullTimeLearningEndDate,
                eaModules,
                emaSections,
                omaSections);
        this.id = id;
    }

    private void addEducationActivityModule(Set<EAModule> modules) {
        modules.forEach(module -> module.etp(this));
        this.eaModules = modules;
    }

    public void addEducationMethodicalSections(Set<EMASection> sections) {
        sections.forEach(section -> section.etp(this));
        this.emaSections = sections;
    }

    public void addOrganizationallyMethodicalSections(Set<OMASection> sections) {
        sections.forEach(section -> section.etp(this));
        this.omaSections = sections;
    }

    //TODO: объекты нижнего уровня ничего не должны знать про объекты вернхнего!!!
    public Set<EAModule> bindWithThisModule(
            @NonNull Collection<EAModuleDTO> moduleDTOs
    ) {
        Set<EAModule> modules = moduleDTOs
                .stream()
                .map(EAModule::new)
                .collect(toSet());
        modules.forEach(module -> module.etp(this));

        return modules;
    }

    public void addModule(@NonNull EAModule module) {
        module.etp(this);
        this.eaModules.add(module);
    }
}
