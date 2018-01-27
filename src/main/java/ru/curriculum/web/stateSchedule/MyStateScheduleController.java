package ru.curriculum.web.stateSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.stateSchedule.service.StateScheduleCRUDService;
import ru.curriculum.web.View;

@Controller
@RequestMapping(path = Routes.myStateSchedule)
public class MyStateScheduleController {

    @Autowired
    private StateScheduleCRUDService stateScheduleCRUDService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("stateSchedule", stateScheduleCRUDService.findMy());
        return View.STATE_SCHEDULE_LIST;
    }
}
