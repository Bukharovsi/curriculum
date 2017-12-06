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
import ru.curriculum.domain.admin.user.entity.User;
import ru.curriculum.service.UserCRUDService;
import ru.curriculum.service.UserDto;
import ru.curriculum.web.View;

import javax.validation.Valid;

import static ru.curriculum.web.Redirect.redirectTo;

@Controller
@RequestMapping(path = Routes.users)
public class UserManagmentController {
    @Autowired
    private UserCRUDService userCRUDService;

    @RequestMapping(method = RequestMethod.GET)
    public String usersList(Model model) {
        model.addAttribute("users", userCRUDService.findAllUsers());

        return View.USERS_LIST;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewUserForm(Model model) {
        model.addAttribute("user", new UserDto());

        return View.USER_FORM;
    }

    @RequestMapping(value = "/edit/{id}")
    public String getEditUserForm(@PathVariable("id") Integer id,  Model model) {
        model.addAttribute("user", userCRUDService.getUser(id));

        return View.USER_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult errors) {
        if(errors.hasErrors()) {
            return View.USER_FORM;
        }
        userCRUDService.create(userDto);

        return redirectTo(Routes.users);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult errors) {
        if(errors.hasErrors()) {
            return View.USER_FORM;
        }
        userCRUDService.update(userDto);

        return redirectTo(Routes.users);
    }

    //TODO: брать нормальный роут
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Integer id) {
        userCRUDService.delete(id);

        return redirectTo(Routes.users);
    }
}
