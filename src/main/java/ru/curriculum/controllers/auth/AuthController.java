package ru.curriculum.controllers.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.curriculum.application.route.Routes;


@Controller
public class AuthController {

    @RequestMapping(Routes.login)
    public String login() {
        return "login";
    }

    @RequestMapping(Routes.accessDenied)
    public String accessDenied() {
        return "/error/access-denied";
    }
}
