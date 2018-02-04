package ru.curriculum.domain.admin.web;

import boot.IntegrationWebBoot;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ErrorControllerTest extends IntegrationWebBoot {

    @Autowired
    private CuratorRepository curatorRepository;

    @After
    public void tearDown() {
        curatorRepository.deleteAll();
    }

    @Test
    public void tryToGetNonExistentUser_mustReturnErrorPage() throws Exception {
        mockMvc.perform(get("/admin/curators/edit/19999"))
                .andExpect(view().name("error/errorPage"));
    }
}
