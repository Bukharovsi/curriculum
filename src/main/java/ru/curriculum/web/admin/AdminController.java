package ru.curriculum.web.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.UserCRUDService;
import ru.curriculum.service.UserDto;
import ru.curriculum.web.View;

import static ru.curriculum.web.Redirect.*;

// TODO: может быть отдельный контроллер для управления пользователями и преподователями
@Controller
public class AdminController {
    @Autowired
    private UserCRUDService userCRUDService;

    @RequestMapping(value = Routes.admin, method = RequestMethod.GET)
    public String adminIndex() {
        return View.ADMIN;
    }

    @RequestMapping(value = Routes.users, method = RequestMethod.GET)
    public String usersList(Model model) {
        model.addAttribute("users", userCRUDService.findAllUsers());

        return View.USERS_LIST;
    }

    @RequestMapping(value = Routes.userForm, method = RequestMethod.GET)
    public String getUserForm(Model model) {
        model.addAttribute("userDto", new UserDto());

        return View.USER_FORM;
    }

    @RequestMapping(value = Routes.createUser, method = RequestMethod.POST)
    public String createUser(@ModelAttribute UserDto userDto, BindingResult errors, Model model) {
        userCRUDService.createUser(userDto);

        return redirectTo(Routes.users);
    }

    //TODO: брать нормальный роут
    @RequestMapping(value = "/admin/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer id) {
        userCRUDService.deleteUser(id);

        return redirectTo(Routes.users);
    }
}
