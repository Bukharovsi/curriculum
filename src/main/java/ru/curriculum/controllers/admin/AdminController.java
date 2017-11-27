package ru.curriculum.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.curriculum.application.route.Routes;

@Controller
public class AdminController {

    @RequestMapping(Routes.admin)
    public String adminIndex() {
        return "/admin/index";
    }
}
