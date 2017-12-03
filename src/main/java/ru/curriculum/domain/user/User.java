package ru.curriculum.domain.user;


import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "users")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    public User() {}

    public User(String username, String password) {
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
}
