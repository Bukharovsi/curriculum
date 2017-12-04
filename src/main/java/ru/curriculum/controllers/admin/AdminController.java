package ru.curriculum.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.curriculum.application.route.Routes;

@Controller
public class AdminController {

    @RequestMapping(Routes.admin)
    public String adminIndex() {
        return "/admin/index";
    }

    @RequestMapping(Routes.users)
    public String usersManagement() {
        System.out.println("XUI");
        return "/admin/users/users";
    }
}
