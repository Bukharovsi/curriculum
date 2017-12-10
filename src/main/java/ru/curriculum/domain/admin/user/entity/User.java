package ru.curriculum.domain.admin.user.entity;


import lombok.NonNull;
import org.hibernate.annotations.Target;
import ru.curriculum.service.UserDTO;

import javax.persistence.*;

@Entity
@Table(name = "users")
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

    public User() {
    }

    public User(
            @NonNull String username,
            String password,
            String surname,
            String firstname,
            String lastname
    ) {
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

    public Integer id() {
        return id;
    }

    public String username() {
        return username;
    }

    public Password password() {
        return password;
    }

    public String firstName() {
        return firstname;
    }

    public String surname() {
        return surname;
    }

    public String lastName() {
        return lastname;
    }

    public Role role() {
        return role;
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
}
