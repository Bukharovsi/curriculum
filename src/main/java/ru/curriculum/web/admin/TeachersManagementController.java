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
import ru.curriculum.service.teacher.TeacherDTO;
import ru.curriculum.web.View;

import javax.validation.Valid;


import static ru.curriculum.web.Redirect.*;

@Controller
@RequestMapping(path = Routes.teachers)
public class TeachersManagementController {
    @Autowired
    private TeacherCRUDService teacherCRUDService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("teachers", teacherCRUDService.findAll());

        return View.TEACHERS_LIST;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("academicDegrees", teacherCRUDService.getAcademicDegrees());

        return View.TEACHER_FORM;
    }

    @RequestMapping(value = "/newFromUser", method = RequestMethod.GET)
    public String getNewTeacherFromUserForm() {
        return View.TEACHER_FROM_USER_FROM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createTeacher(
            @ModelAttribute("teacher") @Valid TeacherDTO teacherDTO,
            BindingResult teacherBindingResult,
            Model model
    ) {
        if(teacherBindingResult.hasErrors()) {
            model.addAttribute("academicDegrees", teacherCRUDService.getAcademicDegrees());

            return View.TEACHER_FORM;
        }
        teacherCRUDService.create(teacherDTO);

        return redirectTo(Routes.teachers);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditUserForm(@PathVariable Integer id, Model model) {
        model.addAttribute("teacher", teacherCRUDService.get(id));
        model.addAttribute("academicDegrees", teacherCRUDService.getAcademicDegrees());

        return View.TEACHER_FORM;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTeacher(@PathVariable Integer id) {
       teacherCRUDService.delete(id);

       return redirectTo(Routes.teachers);
    }
}
