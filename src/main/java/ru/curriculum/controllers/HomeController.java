package ru.curriculum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.curriculum.application.route.Routes;

@Controller
public class HomeController {

    @RequestMapping(Routes.index)
    public String index() {
        return "index"; // todo брать из Path.index например
    }
}
