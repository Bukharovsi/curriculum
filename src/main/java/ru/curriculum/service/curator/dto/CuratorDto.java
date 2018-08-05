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
public class CuratorDto implements Serializable {

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

    private Boolean isAdmin;

    public CuratorDto() {
        this.isTeacher = false;
    }

    public CuratorDto(Curator curator) {
        this.id = curator.id();
        this.username = curator.login();
        this.firstName = curator.firstName();
        this.surname = curator.surname();
        this.patronymic = curator.patronymic();
        this.fullName = curator.fullName();
        this.isTeacher = curator.isTeacher();
        this.isAdmin = curator.isAdmin();
    }

    public String fio() {
        return String.format("%s %s %s",
                surname!=null ? surname : "",
                firstName!=null ? firstName: "",
                patronymic!=null ? patronymic: ""
        );
    }
}
