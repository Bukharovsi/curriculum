package ru.curriculum.domain.admin.curator.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Target;
import ru.curriculum.domain.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Table(name = "curator")
@EqualsAndHashCode(of = { "id", "login" })
@Getter @Setter
@Accessors(fluent = true)
@ToString
public class Curator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    @Embedded
    @AttributeOverride(column = @Column(name = "password"), name = "password")
    @Target(Password.class)
    private Password password;

    private String firstName;

    private String surname;

    private String patronymic;

    @ManyToOne(targetEntity = Role.class)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "curator")
    private Teacher teacher;

    public Curator() {
        this.firstName = "";
        this.surname = "";
        this.patronymic = "";
        this.role = new Role("curator", "Пользователь");
    }

    public Curator(
            Integer id,
            String login,
            String password,
            String surname,
            String firstName,
            String patronymic
    ) {
        this();
        this.id = id;
        this.login = login;
        this.password = new Password(password);
        this.surname = null != surname ? surname : "";
        this.firstName = null != firstName ? firstName : "";
        this.patronymic = null != patronymic ? patronymic : "";
    }

    public Curator(
            @NonNull String login,
            String password,
            String surname,
            String firstName,
            String patronymic
    ) {
        this();
        this.login = login;
        this.password = new Password(password);
        this.surname = null != surname ? surname : "";
        this.firstName = null != firstName ? firstName : "";
        this.patronymic = null != patronymic ? patronymic : "";
    }

    public Curator(String login, String password) {
        this();
        this.login = login;
        this.password = new Password(password);
    }

    public void assignRole(Role role) {
        this.role = role;
    }

    public String fullName() {
        String firstNameShort = !firstName.isEmpty() ?
                firstName.substring(0, 1).toUpperCase().concat(".") : "";
        String patronymicShort = !patronymic.isEmpty() ?
                patronymic.substring(0, 1).toUpperCase().concat(".") : "";

        return surname.concat(" ").concat(firstNameShort).concat(patronymicShort);
    }

    public boolean isTeacher() {
        return null != teacher;
    }


    public void changePassword(String password) {
        this.password = new Password(password);
    }
}
