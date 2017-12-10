package ru.curriculum.domain.teacher;

import lombok.EqualsAndHashCode;
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.service.teacher.TeacherDTO;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
@EqualsAndHashCode(of = {"id"})
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstname;
    private String surname;
    private String lastname;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    private AcademicDegree academicDegree;
    private String placeOfWork;
    private String position;
    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.PERSIST})
    private User user;

    public Teacher() {
    }

    public Teacher(
            String firstname,
            String surname,
            String lastname,
            AcademicDegree academicDegree) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.academicDegree = academicDegree;
    }

    public Teacher(TeacherDTO teacherDTO) {
        this.surname = teacherDTO.getSurname();
        this.firstname = teacherDTO.getFirstname();
        this.lastname = teacherDTO.getLastname();
//        this.academicDegree = new AcademicDegree(teacherDTO.getAcademicDegreeDTO());
        this.academicDegree = teacherDTO.getAcademicDegree();
        this.placeOfWork = teacherDTO.getPlaceOfWork();
        this.position = teacherDTO.getPosition();
    }

    public Integer id() {
        return id;
    }

    public String firstname() {
        return firstname;
    }

    public String surname() {
        return surname;
    }

    public String lastname() {
        return lastname;
    }

    public AcademicDegree academicDegree() {
        return academicDegree;
    }

    public String placeOfWork() {
        return placeOfWork;
    }

    public String position() {
        return position;
    }

    public User user() {
        return user;
    }

    public boolean isUser() {
        return null != user;
    }
}
