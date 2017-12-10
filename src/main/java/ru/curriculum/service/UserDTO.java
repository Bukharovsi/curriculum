package ru.curriculum.service;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.service.validation.PasswordConstraint;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@PasswordConstraint
@ToString
public class UserDTO implements Serializable {
    private Integer id;
    @Size(min = 3, max = 25)
    private String username;
    private String password;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String firstname;
    private String lastname;

    public UserDTO() {
    }

    public UserDTO(User user) {
        this.id = user.id();
        this.username = user.username();
        this.firstname = user.firstName();
        this.surname = user.surname();
        this.lastname = user.lastName();
    }

    public boolean passwordIsPresent() {
        return null != password && !password.isEmpty();
    }
}
