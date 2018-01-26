package ru.curriculum.domain.admin;

import boot.IntegrationWebBoot;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import ru.curriculum.domain.admin.curator.entity.Curator;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser
public class CuratorManagementControllerTest extends IntegrationWebBoot {
    @Autowired
    private CuratorRepository curatorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private List<Curator> curators;

    @Before
    public void setUp() {
        super.setUp();
        curators = createCreate();
    }

    @After
    public void tearDown() {
        curatorRepository.deleteAll();
    }

    private List<Curator> createCreate() {
        Curator ivan = new Curator(
                "ivan123",
                "123",
                "Иванов",
                "Иван",
                "Иванович");
        Curator petr = new Curator(
                "igor231",
                "231",
                "Петров",
                "Петр",
                "Петров");
        List<Curator> curators = new ArrayList<>();
        curators.add(ivan);
        curators.add(petr);

        return (List<Curator>) curatorRepository.save(curators);
    }

    @Test
    public void getCurator() throws Exception {
        String url = "/admin/curators/edit/" + curators.get(0).id();
        mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(view().name("admin/curators/curatorForm"))
                .andExpect(model().attributeDoesNotExist("password"))
                .andDo(print());
    }

    @Test
    public void getCuratorCreatingForm() throws Exception {
        mockMvc.perform(get("/admin/curators/new"))
                .andExpect(view().name("admin/curators/curatorForm"));
    }

    @Test
    public void createCurator() throws Exception {
        mockMvc.perform(post("/admin/curators")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "test")
                .param("password", "123")
                .param("firstName", "test")
                .param("surname", "test"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/curators"))
                .andDo(print());

        Curator newCurator = curatorRepository.findByLogin("test");

        assertNotNull("Curator was created", newCurator);
        assertNotNull("Default role created", newCurator.role());
        assertEquals("curator", newCurator.role().code());
    }

    @Test
    public void editCurator() throws Exception {
        mockMvc.perform(put("/admin/curators")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", curators.get(0).id().toString())
                .param("firstName", curators.get(0).firstName())
                .param("surname", curators.get(0).surname())
                .param("patronymic", "Васильевич"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/curators"))
                .andDo(print());
        Curator curator = curatorRepository.findOne(curators.get(0).id());

        assertEquals("Васильевич", curator.patronymic());
        assertTrue(
                "When editing curator and no change password the password remains the same",
                passwordEncoder.matches( "123", curator.password().hash()));
        assertNotNull("Role not changed", curator.role());
        assertEquals("curator", curator.role().code());
    }

    @Test
    public void changeCuratorPassword() throws Exception {
        mockMvc.perform(put("/admin/curators")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", curators.get(0).id().toString())
                .param("username", curators.get(0).login())
                .param("password", "444")
                .param("firstName", curators.get(0).firstName())
                .param("surname", curators.get(0).surname())
                .param("patronymic", curators.get(0).patronymic()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/curators"))
                .andDo(print());
        Curator curator = curatorRepository.findOne(curators.get(0).id());

        assertTrue(
                "Password successfully changed",
                passwordEncoder.matches( "444", curator.password().hash()));
    }


    @Test
    public void createCuratorWithActuallyExistLogin_mustBeOneError() throws Exception {
        // Can not be curators with the same "curatorLogin"
        mockMvc.perform(post("/admin/curators")
                .accept(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", curators.get(0).login())
                .param("password", "123")
                .param("firstName", "test")
                .param("surname", "test"))
                .andExpect(view().name("admin/curators/curatorForm"))
                .andExpect(model().errorCount(1))
                .andDo(print());
    }

    @Test
    public void deleteCurator() throws Exception {
        String url = "/admin/curators/delete/" + curators.get(1).id();
        mockMvc.perform(get(url))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/admin/curators"))
                .andDo(print());

        Curator curator = curatorRepository.findOne(curators.get(1).id());

        assertNull("Curator deleted", curator);
    }
}

