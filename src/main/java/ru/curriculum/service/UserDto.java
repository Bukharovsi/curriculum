package ru.curriculum.service;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.admin.user.entity.User;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
public class UserDto implements Serializable {
    private Integer id;
    @Size(min = 3, max = 25)
    private String username;
    @Size(min = 3, max = 12)
    private String password;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String firstname;
    private String lastname;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.id();
        this.username = user.username();
//        this.password = user.password();
        this.firstname = user.firstName();
        this.surname = user.surname();
        this.lastname = user.lastName();
    }

    public boolean passwordIsPresent() {
        return null != password;
    }
}
