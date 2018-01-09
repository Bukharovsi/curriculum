package ru.curriculum.domain.etp.entity.educationMethodicalActivity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.entity.IModule;
import ru.curriculum.domain.etp.entity.Plan;
import ru.curriculum.domain.etp.entity.educationActivity.EASection;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
 * EMAModule - Educational Methodical Section.
 * Модуль учебно-методической деятельности.
 */
@Entity
@Table(name = "education_methodical_module")
@Getter
@Accessors(fluent = true)
public class EMAModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(
            mappedBy = "emaModule",
            targetEntity = EMASection.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<EMASection> sections;
    @ManyToOne
    @JoinColumn(name = "etp_id")
    @Setter
    private ETP etp;

    public EMAModule() {
        this.sections = new HashSet<>();
    }

    public EMAModule(String name, Set<EMASection> sections) {
        this.name = name;
        this.addSections(sections);
    }

    private void addSections(Set<EMASection> sections) {
        sections.forEach(section -> section.emaModule(this));
        this.sections = sections;
    }
}
