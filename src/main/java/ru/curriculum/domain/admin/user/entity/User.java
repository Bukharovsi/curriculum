package ru.curriculum.domain.admin.user.entity;


import lombok.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

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

    public Integer id() {
        return id;
    }

    public String username() {
        return username;
    }

    public String password() {
        return password;
    }

    public Role role() {
        return role;
    }

    public void assignRole(Role role) {
        this.role = role;
    }
}
