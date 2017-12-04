package ru.curriculum.service;

import lombok.Getter;
import lombok.Setter;
import ru.curriculum.domain.admin.user.entity.User;

import java.io.Serializable;

@Setter
@Getter
public class UserDto implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String firstname;
    private String surname;
    private String lastname;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.id();
        this.username = user.username();
        this.password = user.password();
        this.firstname = user.firstName();
        this.surname = user.surname();
        this.lastname = user.lastName();
    }
}
