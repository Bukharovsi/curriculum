package ru.curriculum.service.curator.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.service.curator.validation.PasswordConstraint;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Setter
@Getter
@PasswordConstraint
@ToString
public class CuratorDTO implements Serializable {

    private Integer id;

    @Size(min = 3, max = 25, message = "Длина \"Лоигина\" должна быть меньше {max} и больше {min}")
    private String username;

    private String password;

    @NotEmpty(message = "Необходимо заполнить поле \"Фамилия\"")
    private String surname;

    @NotEmpty(message = "Необходимо заполнить поле \"Имя\"")
    private String firstName;

    private String patronymic;

    private String fullName;

    private Boolean isTeacher;

    public CuratorDTO() {
        this.isTeacher = false;
    }

    public CuratorDTO(Curator curator) {
        this.id = curator.id();
        this.username = curator.login();
        this.firstName = curator.firstName();
        this.surname = curator.surname();
        this.patronymic = curator.patronymic();
        this.fullName = curator.fullName();
        this.isTeacher = curator.isTeacher();
    }

    public boolean passwordIsPresent() {
        return null != password && !password.isEmpty();
    }

    public String fio() {
        return String.format("%s %s %s",
                surname!=null ? surname : "",
                firstName!=null ? firstName: "",
                patronymic!=null ? patronymic: ""
        );
    }
}
