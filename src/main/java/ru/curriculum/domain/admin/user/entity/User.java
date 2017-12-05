package ru.curriculum.domain.admin.user.entity;


import lombok.NonNull;
import ru.curriculum.service.UserDto;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
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

    // TODO: проверки на корректность логина и пароля
    public User(@NonNull String username, @NonNull String password) {
        this();
        this.username = username;
        this.password = password;
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

    public String password() {
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
}
