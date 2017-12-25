package ru.curriculum.domain.etp.entity.educationActivityModule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.service.etp.dto.EducationActivityModuleDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/*
 * Модуль УТП - "Учебная деятельность". Может содержать неограниченное кол-во разделов.
 */
@Entity
@Table(name = "education_activity_module")
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(exclude = {"educationActivitySections"})
public class EducationActivityModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(
            mappedBy = "educationActivityModule",
            targetEntity = EducationActivitySection.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EducationActivitySection> educationActivitySections;
    @ManyToOne
    @JoinColumn(name = "etp_id")
    @Setter
    private ETP etp;

    public EducationActivityModule() {
        this.educationActivitySections = new HashSet<>();
    }

    public EducationActivityModule(String name, Set<EducationActivitySection> educationActivitySections) {
        this();
        this.name = name;
        this.educationActivitySections = bindWithThisModule(educationActivitySections);
    }

    public EducationActivityModule(EducationActivityModuleDTO moduleDTO) {
        this(
                moduleDTO.getName(),
                moduleDTO.getSections()
                        .stream()
                        .map(EducationActivitySection::new)
                        .collect(toSet()));
        this.id = moduleDTO.getId();
    }

    private Set<EducationActivitySection> bindWithThisModule(@NonNull Set<EducationActivitySection> sections) {
        sections.forEach(section -> section.educationActivityModule(this));

        return sections;
    }
}
