package ru.curriculum.domain.admin.user.entity;


import lombok.NonNull;
import org.hibernate.annotations.Target;
import ru.curriculum.service.UserDto;

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

    public User(@NonNull String username, @NonNull String password) {
        this();
        this.username = username;
        this.password = new Password(password);
    }

    public User(
            String username,
            String password,
            String surname,
            String firstname,
            String lastname
    ) {
        this(username, password);
        this.surname = surname;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(UserDto userDto) {
        this(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFirstname(),
                userDto.getSurname(),
                userDto.getLastname());
        this.id = userDto.getId();
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

    public void updatePrincipal(UserDto userDto) {
        this.username = userDto.getUsername();
        this.firstname = userDto.getFirstname();
        this.lastname = userDto.getLastname();
        this.surname = userDto.getSurname();
        if(userDto.passwordIsPresent()) {
            this.password = new Password(userDto.getPassword());
        }
    }

    public void changePassword(String password) {
        this.password = new Password(password);
    }
}
