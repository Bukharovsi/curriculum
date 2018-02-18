package ru.curriculum.domain.stateSchedule.entity;

import lombok.*;
import lombok.experimental.Accessors;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.organization.entity.Division;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Государственная программа, на основании которой формируется УТП
 */
@Entity
@Table(name = "state_program")
@Getter
@Setter
@Accessors(fluent = true)
@Builder
@ToString(exclude = "internships")
@EqualsAndHashCode(exclude = "internships")
public class StateProgram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String targetAudience;

    private String name;

    @OneToOne(targetEntity = StudyMode.class, fetch = FetchType.EAGER)
    private StudyMode mode;

    @OneToOne(targetEntity = ImplementationForm.class, fetch = FetchType.EAGER)
    private ImplementationForm implementationForm;

    @OneToMany(
            mappedBy = "stateProgram",
            targetEntity = Internship.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Internship> internships;

    private Integer lernerCount;

    private Integer groupCount;

    private Integer countOfHoursPerLerner;

    private Date dateStart;

    private Date dateFinish;

    @OneToOne(targetEntity = Division.class, fetch = FetchType.EAGER)
    private Division responsibleDepartment;

    @OneToOne(targetEntity = Curator.class, fetch = FetchType.EAGER)
    private Curator curator;

    private String address;

    public StateProgram() {
        this.internships = new HashSet<>();
    }

    public StateProgram(
            Integer id,
            String targetAudience,
            String name, StudyMode mode,
            ImplementationForm implementationForm,
            Set<Internship> internships,
            Integer lernerCount,
            Integer groupCount,
            Integer countOfHoursPerLerner,
            Date dateStart,
            Date dateFinish,
            Division responsibleDepartment,
            Curator curator,
            String address
    ) {
        this();
        this.id = id;
        this.targetAudience = targetAudience;
        this.name = name;
        this.mode = mode;
        this.implementationForm = implementationForm;
        this.setInternship(internships);
        this.lernerCount = lernerCount;
        this.groupCount = groupCount;
        this.countOfHoursPerLerner = countOfHoursPerLerner;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.responsibleDepartment = responsibleDepartment;
        this.curator = curator;
        this.address = address;
    }

    private void setInternship(Set<Internship> internships) {
        if(null != internships) {
            internships.forEach(internship -> internship.stateProgram(this));
            this.internships = internships;
        } else {
            this.internships = new HashSet<>();
        }
    }
}
