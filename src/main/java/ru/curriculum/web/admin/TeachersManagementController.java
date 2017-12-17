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
import ru.curriculum.service.teacher.TeacherCRUDService;
import ru.curriculum.service.teacher.dto.TeacherDTO;
import ru.curriculum.service.teacher.factory.TeacherDTOFactory;
import ru.curriculum.service.user.UserCRUDService;
import ru.curriculum.web.View;

import javax.validation.Valid;


import static ru.curriculum.web.Redirect.*;

@Controller
@RequestMapping(path = Routes.teachers)
public class TeachersManagementController {
    @Autowired
    private TeacherCRUDService teacherCRUDService;
    @Autowired
    private UserCRUDService userCRUDService;
    @Autowired
    private TeacherDTOFactory teacherDTOFactory;

    // TODO: может перейти на new ModelAndView можно будет выделить отдельный класс для построения этой херни
    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("teachers", teacherCRUDService.findAll());

        return View.TEACHERS_LIST;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("academicDegrees", teacherCRUDService.getAcademicDegrees());
        model.addAttribute("userAccounts", userCRUDService.getFreeAccounts());

        return View.TEACHER_FORM;
    }

    @RequestMapping(value = "/newFromUser/{userId}", method = RequestMethod.GET)
    public String getNewTeacherFromUserForm(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("teacher", teacherDTOFactory.createTeacherDTOBasedOnUser(userId));
        model.addAttribute("academicDegrees", teacherCRUDService.getAcademicDegrees());
        model.addAttribute("userAccounts", userCRUDService.getFreeAccounts());

        return View.TEACHER_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createTeacher(
            @ModelAttribute("teacher") @Valid TeacherDTO teacherDTO,
            BindingResult teacherBindingResult,
            Model model
    ) {
        if(teacherBindingResult.hasErrors()) {
            model.addAttribute("academicDegrees", teacherCRUDService.getAcademicDegrees());
            model.addAttribute("userAccounts", userCRUDService.getFreeAccounts());

            return View.TEACHER_FORM;
        }
        teacherCRUDService.create(teacherDTO);

        return redirectTo(Routes.teachers);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditUserForm(@PathVariable Integer id, Model model) {
        model.addAttribute("teacher", teacherCRUDService.get(id));
        model.addAttribute("academicDegrees", teacherCRUDService.getAcademicDegrees());
        model.addAttribute("userAccounts", userCRUDService.getFreeAccounts());

        return View.TEACHER_FORM;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTeacher(@PathVariable Integer id) {
       teacherCRUDService.delete(id);

       return redirectTo(Routes.teachers);
    }
}
