package ru.curriculum.domain.admin.web;

import boot.IntegrationWebBoot;
import org.junit.After;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.curriculum.application.route.Routes;
import ru.curriculum.domain.etp.entity.ETP;
import ru.curriculum.domain.etp.repository.ETPRepository;
import ru.curriculum.domain.helper.StateProgramTestHelper;
import ru.curriculum.domain.stateSchedule.entity.StateProgram;
import ru.curriculum.web.View;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WithMockUser
public class ETPControllerTest extends IntegrationWebBoot {
    @Autowired
    private StateProgramTestHelper stateProgramHelper;
    @Autowired
    private ETPRepository etpRepository;

    @After
    public void tearDown() {
        etpRepository.deleteAll();
        stateProgramHelper.deleteAll();
    }

    @Test
    public void getEtpTemplateForm() throws Exception {
        StateProgram program = stateProgramHelper.createAndSaveStateProgram();
        mockMvc.perform(get(Routes.etp + "/etpTemplate/" + program.id())
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(view().name(View.ETP_FORM));
    }

    @Test
    public void getEtpTemplateFormFromNoneExistenceStateProgram_mustBeError() throws Exception {
        mockMvc.perform(get(Routes.etp + "/etpTemplate/9999")
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(view().name(View.ERROR_PAGE));
    }

    @Test
    public void tryToCreateETPFromAlreadyCreatedStateProgram_mustBeError() throws Exception {
        StateProgram program = stateProgramHelper.createAndSaveStateProgram();
        ETP etp = getETP();
        etp.stateProgramId(program.id());
        etpRepository.save(etp);

        mockMvc.perform(get(Routes.etp + "/etpTemplate/" + program.id())
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andDo(print())
                .andExpect(view().name(View.ERROR_PAGE));
    }

    private ETP getETP() {
        return new ETP(
                "Education plan",
                "Teach all teachers",
                new Date(2),
                new Date(3),
                new Date(2),
                new Date(3)
        );
    }
}
