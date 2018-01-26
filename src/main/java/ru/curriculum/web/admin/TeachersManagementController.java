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
import ru.curriculum.service.directories.academicDegree.AcademicDegreeFindService;
import ru.curriculum.service.directories.staffTable.StaffTableFindService;
import ru.curriculum.service.teacher.TeacherCRUDService;
import ru.curriculum.service.teacher.dto.TeacherDTO;
import ru.curriculum.service.teacher.factory.TeacherDTOFactory;
import ru.curriculum.service.user.AccountService;

import javax.validation.Valid;


import static ru.curriculum.web.Redirect.*;
import static ru.curriculum.web.View.*;

@Controller
@RequestMapping(path = Routes.teachers)
public class TeachersManagementController {
    @Autowired
    private TeacherCRUDService teacherCRUDService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private StaffTableFindService staffTableFindService;
    @Autowired
    private AcademicDegreeFindService academicDegreeFindService;
    @Autowired
    private TeacherDTOFactory teacherDTOFactory;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("teachers", teacherCRUDService.findAll());

        return TEACHERS_LIST;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDTO());
        model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
        model.addAttribute("userAccounts", accountService.getFreeAccounts());
        model.addAttribute("staffTable", staffTableFindService.findAll());

        return TEACHER_FORM;
    }

    @RequestMapping(value = "/newFromUser/{userId}", method = RequestMethod.GET)
    public String getNewTeacherFromUserForm(@PathVariable("userId") Integer userId, Model model) {
        model.addAttribute("teacher", teacherDTOFactory.createTeacherDTOBasedOnUser(userId));
        model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
        model.addAttribute("userAccounts", accountService.getFreeAccounts());
        model.addAttribute("staffTable", staffTableFindService.findAll());

        return TEACHER_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createTeacher(
            @ModelAttribute("teacher") @Valid TeacherDTO teacherDTO,
            BindingResult teacherBindingResult,
            Model model
    ) {
        if(teacherBindingResult.hasErrors()) {
            model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
            model.addAttribute("userAccounts", accountService.getFreeAccounts());
            model.addAttribute("staffTable", staffTableFindService.findAll());

            return TEACHER_FORM;
        }
        teacherCRUDService.create(teacherDTO);

        return redirectTo(Routes.teachers);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditUserForm(@PathVariable Integer id, Model model) {
        TeacherDTO teacherDTO = teacherCRUDService.get(id);
        model.addAttribute("teacher", teacherDTO);
        model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
        model.addAttribute("userAccounts", accountService.getFreeAccountsAndTeacherAccount(teacherDTO));
        model.addAttribute("staffTable", staffTableFindService.findAll());

        return TEACHER_FORM;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTeacher(@PathVariable Integer id) {
       teacherCRUDService.delete(id);

       return redirectTo(Routes.teachers);
    }
}
