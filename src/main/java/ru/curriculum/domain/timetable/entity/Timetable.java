package ru.curriculum.domain.timetable.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import ru.curriculum.domain.etp.entity.ETP;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "timetable")
@Accessors(fluent = true)
@Getter
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate beginDate;

    private LocalDate endDate;

    private String theme;

    @OneToMany(
            mappedBy = "timetable",
            targetEntity = SchoolDay.class,
            fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<SchoolDay> schoolDays;

    @OneToOne(targetEntity = ETP.class)
    @JoinColumn(name = "etp_id")
    public ETP createdFrom;

    public Timetable() {
        this.schoolDays = new HashSet<>();
    }

    public Timetable(
            LocalDate beginDate,
            LocalDate endDate,
            String theme,
            @NonNull Set<SchoolDay> schoolDays,
            ETP etp
    ) {
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.theme = theme;
        this.addSchoolDays(schoolDays);
        this.createdFrom = etp;
    }

    public Timetable(
            Integer id,
            LocalDate beginDate,
            LocalDate endDate,
            String theme,
            @NonNull Set<SchoolDay> schoolDays,
            ETP etp
    ) {
        this.id = id;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.theme = theme;
        this.addSchoolDays(schoolDays);
        this.createdFrom = etp;
    }

    private void addSchoolDays(Set<SchoolDay> schoolDays) {
        schoolDays.forEach(schoolDay -> schoolDay.timetable(this));
        this.schoolDays = schoolDays;
    }
}
