package ru.curriculum.domain.teacher.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.directories.academicDegree.AcademicDegree;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "teacher")
@EqualsAndHashCode(of = {"id"})
@Getter
@Accessors(fluent = true)
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String surname;

    private String firstName;

    private String patronymic;

    @OneToOne(fetch = FetchType.EAGER)
    private AcademicDegree academicDegree;

    private String placeOfWork;

    private String positionHeld;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TeacherType type;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "curator_id")
    private Curator curator;

    public Teacher() {
        this.firstName = "";
        this.surname = "";
        this.patronymic = "";
        this.placeOfWork = "ГАОУ ДПО Институт Развития Образования РТ";
        this.type = TeacherType.STAFF;
    }

    public Teacher(
            Integer id,
            @NonNull String surname,
            @NonNull String firstName,
            @NotNull String patronymic,
            @NonNull AcademicDegree academicDegree,
            String placeOfWork,
            String positionHeld,
            TeacherType type
    ) {
        this();
        this.id = id;
        this.surname = surname;
        this.firstName = firstName;
        this.patronymic = patronymic;
        if(null != type) {
            this.type = type;
        }
        this.academicDegree = academicDegree;
        if(null != placeOfWork) {
            this.placeOfWork = placeOfWork;
        }
        this.positionHeld = positionHeld;
    }

    public String fullName() {
        String firstNameShort = !firstName.isEmpty() ?
                firstName.substring(0, 1).toUpperCase().concat(".") : "";
        String patronymicShort = !patronymic.isEmpty() ?
                patronymic.substring(0, 1).toUpperCase().concat(".") : "";

        return surname.concat(" ").concat(firstNameShort).concat(patronymicShort);
    }

    public Curator curatorProfile() {
        return curator;
    }

    public void assignCuratorProfile(Curator curator) {
        this.curator = curator;
    }

    public boolean hasCuratorProfile() {
        return null != curator;
    }

    public void deleteCuratorProfile() {
        this.curator = null;
    }
}
