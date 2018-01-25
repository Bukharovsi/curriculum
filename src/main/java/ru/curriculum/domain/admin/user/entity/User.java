package ru.curriculum.domain.admin.user.entity;


import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Target;
import ru.curriculum.domain.teacher.entity.Teacher;

import javax.persistence.*;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = { "id", "username" })
@Getter @Setter
@Accessors(fluent = true)
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Embedded
    @AttributeOverride(column = @Column(name = "password"), name = "password")
    @Target(Password.class)
    private Password password;

    private String firstName;

    private String surname;

    private String patronymic;

    @ManyToOne(targetEntity = Role.class)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private Teacher teacher;

    public User() {
        this.firstName = "";
        this.surname = "";
        this.patronymic = "";
        this.role = new Role("user", "Пользователь");
    }

    public User(
            Integer id,
            String username,
            String password,
            String surname,
            String firstName,
            String patronymic
    ) {
        this();
        this.id = id;
        this.username = username;
        this.password = new Password(password);
        this.surname = null != surname ? surname : "";
        this.firstName = null != firstName ? firstName : "";
        this.patronymic = null != patronymic ? patronymic : "";
    }

    public User(
            @NonNull String username,
            String password,
            String surname,
            String firstName,
            String patronymic
    ) {
        this();
        this.username = username;
        this.password = new Password(password);
        this.surname = null != surname ? surname : "";
        this.firstName = null != firstName ? firstName : "";
        this.patronymic = null != patronymic ? patronymic : "";
    }

    public User(String username, String password) {
        this();
        this.username = username;
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
