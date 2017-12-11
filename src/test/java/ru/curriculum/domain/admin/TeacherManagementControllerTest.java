package ru.curriculum.domain.admin;

import boot.IntegrationWebBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.curriculum.domain.teacher.AcademicDegree;
import ru.curriculum.domain.teacher.Teacher;
import ru.curriculum.domain.teacher.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.curriculum.domain.teacher.AcademicDegreeCode.*;

public class TeacherManagementControllerTest extends IntegrationWebBoot {
    @Autowired
    private TeacherRepository teacherRepository;
    private List<Teacher> teachers;

    @Before
    public void setUp() {
        super.setUp();
        teachers = createTeachers();
    }

    @After
    public void tearDown() {
        teacherRepository.deleteAll();
    }

    @Test
    public void getTeacherCreatingForm() throws Exception {
        mockMvc.perform(get("/admin/teachers/new"))
                .andExpect(view().name("/admin/teachers/teacherForm"));
    }

    @Test
    public void createTeacher() throws Exception {
        mockMvc.perform(post("/admin/teachers")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("lastname", "test")
                .param("firstname", "test")
                .param("surname", "test")
                .param("academicDegreeCode", PH_D))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/teachers"))
                .andDo(print());
    }

    @Test
    public void editTeacher() throws Exception {
        mockMvc.perform(post("/admin/teachers")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", teachers.get(0).id().toString())
                .param("lastname", teachers.get(0).lastname())
                .param("firstname", teachers.get(0).firstname())
                .param("surname", teachers.get(0).surname())
                .param("academicDegreeCode", PROFESSOR))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/teachers"))
                .andDo(print());
        Teacher teacher = teacherRepository.findOne(teachers.get(0).id());

        assertEquals(PROFESSOR, teacher.academicDegree().code());
    }

    @Test
    public void deleteTeacher() throws Exception {
        String url = "/admin/teachers/delete/" + teachers.get(1).id();
        mockMvc.perform(get(url))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/teachers"))
                .andDo(print());

        Teacher teacher = teacherRepository.findOne(teachers.get(1).id());

        assertNull("Teacher deleted", teacher);
    }

    private List<Teacher> createTeachers() {
        Teacher cook = new Teacher(
                null,
                "Иванов",
                "Иван",
                "Иванович",
                new AcademicDegree(PH_D, "Доктор наук"),
                "Макдоналдс",
                "Жарщик котлет");
        Teacher teacher = new Teacher(
                null,
                "Петров",
                "Петр",
                "Петрович",
                new AcademicDegree(PH_D, "Доктор наук"),
                null,
                null);
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(cook);
        teachers.add(teacher);

        return (List<Teacher>) teacherRepository.save(teachers);
    }
}
