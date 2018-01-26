package ru.curriculum.domain.admin;

import boot.IntegrationWebBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.domain.directories.academicDegree.AcademicDegree;
import ru.curriculum.domain.teacher.entity.Teacher;
import ru.curriculum.domain.teacher.repository.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.curriculum.domain.directories.academicDegree.AcademicDegreeCode.*;

@WithMockUser
public class TeacherManagementControllerTest extends IntegrationWebBoot {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private CuratorRepository curatorRepository;
    private List<Teacher> teachers;
    private Curator curator;

    @Before
    public void setUp() {
        super.setUp();
        curator = createUser();
        teachers = createTeachers();
    }

    @After
    public void tearDown() {
        teacherRepository.deleteAll();
        curatorRepository.deleteAll();
    }

    @Test
    public void getTeacherForm() throws Exception {
        mockMvc.perform(get("/admin/teachers/new"))
                .andExpect(view().name("admin/teachers/teacherForm"));
    }

    @Test
    public void createTeacher() throws Exception {
        mockMvc.perform(post("/admin/teachers")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("patronymic", "test")
                .param("firstName", "test")
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
                .param("patronymic", teachers.get(0).patronymic())
                .param("firstName", teachers.get(0).firstName())
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

    @Test
    public void getNewTeacherFromUserForm() throws Exception {
        mockMvc.perform(get("/admin/teachers/newFromCurator/{id}", curator.id())
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(view().name("admin/teachers/teacherForm"))
                .andExpect(model().attributeExists("teacher"))
                .andExpect(model().attributeExists("academicDegrees"))
                .andExpect(model().attributeExists("curatorProfiles"));
    }

    @Test
    public void createTeacherFromUserAccount_mustBeCreateUserWithAccount() throws Exception {
        mockMvc.perform(post("/admin/teachers")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", teachers.get(0).id().toString())
                .param("patronymic", teachers.get(0).patronymic())
                .param("firstName", teachers.get(0).firstName())
                .param("surname", teachers.get(0).surname())
                .param("academicDegreeCode", PROFESSOR)
                .param("curatorId", curator.id().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/teachers"))
                .andDo(print());
        Teacher teacher = teacherRepository.findOne(teachers.get(0).id());

        assertEquals(curator, teacher.curatorProfile());
    }

    private Curator createUser() {
        Curator curator = new Curator(
                "xui",
                "123",
                "Иванов",
                "Иван",
                "Иванович");
        curatorRepository.save(curator);

        return curator;
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
