package ru.curriculum.web.auth;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.curriculum.application.route.Routes;


@Controller
public class AuthController {

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(Routes.index)
    public String index() {
        return "index";
    }

    @RequestMapping(Routes.login)
    public String login() {
        return "login";
    }

    @RequestMapping(Routes.accessDenied)
    public String accessDenied() {
        return "error/accessDenied";
    }
}
