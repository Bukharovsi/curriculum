package ru.curriculum.web.stateSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramViewDto;
import ru.curriculum.service.stateSchedule.service.ImplementationFormFindService;
import ru.curriculum.service.stateSchedule.service.StateScheduleCRUDService;
import ru.curriculum.service.stateSchedule.service.StudyModeFindService;
import ru.curriculum.service.user.UserCRUDService;
import ru.curriculum.web.View;

@Controller
@RequestMapping(path = Routes.stateSchedule)
public class StateScheduleController {

    @Autowired
    private StateScheduleCRUDService stateScheduleCRUDService;

    @Autowired
    private ImplementationFormFindService implementationFormFindService;

    @Autowired
    private StudyModeFindService studyModeFindService;

    @Autowired
    private UserCRUDService userCRUDService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("stateSchedule", stateScheduleCRUDService.findAll());
        return View.STATE_SCHEDULE_LIST;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/newForm")
    public String newForm(Model model) {
        model.addAttribute("stateProgram", new StateProgramCreationDto());
        model.addAttribute("implementationFormList", implementationFormFindService.findAll());
        model.addAttribute("studyModeList", studyModeFindService.findAll());
        model.addAttribute("userList", userCRUDService.findAllUsers());
        return View.STATE_SCHEDULE_FORM;
    }
}
