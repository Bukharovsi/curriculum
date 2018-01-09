package ru.curriculum.domain.etp.entity.organizationMethodicalActivity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.IModule;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.etp.entity.educationMethodicalActivity.EMASection;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * OMAModule - Organizationally methodical section.
 * Модуль организационно-методической деятельности.
 */
@Entity
@Table(name = "organization_methodical_module") //TODO меняем название
@Getter
@Accessors(fluent = true)
public class OMAModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(
            mappedBy = "omaModule",
            targetEntity = OMASection.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<OMASection> sections;
    @Setter
    @ManyToOne
    @JoinColumn(name = "etp_id")
    private ETP etp;

    public OMAModule() {
        this.sections = new HashSet<>();
    }

    public OMAModule(String name, Set<OMASection> omaSections) {
        this.name = name;
        this.addSections(omaSections);
    }

    private void addSections(Set<OMASection> sections) {
        sections.forEach(section -> section.omaModule(this));
        this.sections = sections;
    }
}
