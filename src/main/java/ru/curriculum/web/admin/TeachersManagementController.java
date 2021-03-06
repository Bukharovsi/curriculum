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
import ru.curriculum.service.teacher.dto.TeacherDto;
import ru.curriculum.service.teacher.factory.TeacherDtoFactory;
import ru.curriculum.service.curator.AccountService;

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
    private TeacherDtoFactory teacherDtoFactory;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("teachers", teacherCRUDService.findAll());

        return TEACHERS_LIST;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewTeacherForm(Model model) {
        model.addAttribute("teacher", new TeacherDto());
        model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
        model.addAttribute("curatorProfiles", accountService.getFreeAccounts());
        model.addAttribute("staffPosition", staffTableFindService.findAll());

        return TEACHER_FORM;
    }

    @RequestMapping(value = "/newFromCurator/{curatorId}", method = RequestMethod.GET)
    public String getNewTeacherFromCuratorForm(@PathVariable("curatorId") Integer curatorId, Model model) {
        model.addAttribute("teacher", teacherDtoFactory.createTeacherDTOBasedOnCurator(curatorId));
        model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
        model.addAttribute("curatorProfiles", accountService.getFreeAccounts());
        model.addAttribute("staffPosition", staffTableFindService.findAll());

        return TEACHER_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createTeacher(
            @ModelAttribute("teacher") @Valid TeacherDto teacherDto,
            BindingResult teacherBindingResult,
            Model model
    ) {
        if(teacherBindingResult.hasErrors()) {
            model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
            model.addAttribute("curatorProfiles", accountService.getFreeAccounts());
            model.addAttribute("staffPosition", staffTableFindService.findAll());

            return TEACHER_FORM;
        }
        teacherCRUDService.create(teacherDto);

        return redirectTo(Routes.teachers);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditCuratorForm(@PathVariable Integer id, Model model) {
        TeacherDto teacherDto = teacherCRUDService.get(id);
        model.addAttribute("teacher", teacherDto);
        model.addAttribute("academicDegrees", academicDegreeFindService.findAll());
        model.addAttribute("curatorProfiles", accountService.getFreeAccountsAndTeacherAccount(teacherDto));
        model.addAttribute("staffPosition", staffTableFindService.findAll());

        return TEACHER_FORM;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteTeacher(@PathVariable Integer id) {
       teacherCRUDService.delete(id);

       return redirectTo(Routes.teachers);
    }
}
