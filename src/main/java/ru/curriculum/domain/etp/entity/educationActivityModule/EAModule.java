package ru.curriculum.domain.etp.entity.educationActivityModule;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.service.etp.dto.EAModuleDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/*
 * EAModule - Education Activity.
 * Модуль УТП - "Учебная деятельность". Может содержать неограниченное кол-во разделов.
 */
@Entity
@Table(name = "education_activity_module")
@Getter
@Accessors(fluent = true)
@EqualsAndHashCode(exclude = {"educationActivitySections"})
public class EAModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(
            mappedBy = "eaModule",
            targetEntity = EASection.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EASection> eaSections;
    @ManyToOne
    @JoinColumn(name = "etp_id")
    @Setter
    private ETP etp;

    public EAModule() {
        this.eaSections = new HashSet<>();
    }

    public EAModule(String name, Set<EASection> eaSections) {
        this();
        this.name = name;
        this.eaSections = bindWithThisModule(eaSections);
    }

    public EAModule(Integer id, String name, Set<EASection> sectionDTOs) {
        this(name, sectionDTOs);
        this.id = id;
    }

    public EAModule(EAModuleDTO moduleDTO) {
        this(
                moduleDTO.getName(),
                moduleDTO.getSections()
                        .stream()
                        .map(EASection::new)
                        .collect(toSet()));
        this.id = moduleDTO.getId();
    }

    private Set<EASection> bindWithThisModule(@NonNull Set<EASection> sections) {
        sections.forEach(section -> section.eaModule(this));

        return sections;
    }
}
