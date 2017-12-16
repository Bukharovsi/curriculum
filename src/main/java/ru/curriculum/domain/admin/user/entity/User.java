package ru.curriculum.domain.admin.user.entity;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Target;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.service.user.dto.UserDTO;

import javax.persistence.*;

@Entity
@Table(name = "users")
@EqualsAndHashCode(of = { "id", "username" })
@Getter
@Accessors(fluent = true)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    @Embedded
    @AttributeOverride(column = @Column(name = "password"), name = "password")
    @Target(Password.class)
    private Password password;
    private String firstname;
    private String surname;
    private String lastname;

    // TODO: Либо много ролей,
    // TODO: либо роль определяетмя разрешениями,
    // TODO: либо ограничимя одной ролью
    @ManyToOne(targetEntity = Role.class)
    private Role role;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "user")
    private Teacher teacher;

    public User() {
        this.role = new Role("user", "Пользователь");
    }

    public User(
            @NonNull String username,
            String password,
            String surname,
            String firstname,
            String lastname
    ) {
        this();
        this.username = username;
        this.password = new Password(password);
        this.surname = surname;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(UserDTO userDTO) {
        this(
                userDTO.getUsername(),
                userDTO.getPassword(),
                userDTO.getSurname(),
                userDTO.getFirstname(),
                userDTO.getLastname());
    }

    public void assignRole(Role role) {
        this.role = role;
    }

    public void updatePrincipal(UserDTO userDTO) {
        this.firstname = userDTO.getFirstname();
        this.lastname = userDTO.getLastname();
        this.surname = userDTO.getSurname();
        if(userDTO.passwordIsPresent()) {
            this.password = new Password(userDTO.getPassword());
        }
    }

    public void changePassword(String password) {
        this.password = new Password(password);
    }

    public boolean isTeacher() {
        return null != teacher;
    }
}
