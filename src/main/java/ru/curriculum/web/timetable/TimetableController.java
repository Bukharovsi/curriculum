package ru.curriculum.web.timetable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.etp.dto.ETPDto;
import ru.curriculum.service.teacher.TeacherCRUDService;
import ru.curriculum.service.timetable.TimetableCRUDService;
import ru.curriculum.service.timetable.dto.TimetableDto;

import javax.validation.Valid;

import static ru.curriculum.web.Redirect.*;
import static ru.curriculum.web.View.*;

@Controller
@RequestMapping(path = Routes.timetable)
public class TimetableController {
    @Autowired
    private TimetableCRUDService timetableCRUDService;
    @Autowired
    private TeacherCRUDService teacherCRUDService;

    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model model) {
        model.addAttribute("timetables", timetableCRUDService.findAll());
        return TIMETABLE_LIST;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("timetable", timetableCRUDService.get(id));
        return TIMETABLE_FORM;
    }

    @RequestMapping(params = "/edit", method = RequestMethod.PUT)
    public String editTimetable(
            @ModelAttribute("timetable") @Valid TimetableDto timetableDto,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            return TIMETABLE_FORM;
        }
        timetableCRUDService.update(timetableDto);
        return redirectTo(Routes.timetable);
    }

    @RequestMapping(value = "/newTimetablesFromEtp", method = {RequestMethod.POST, RequestMethod.PUT})
    public String makeTimetablesFromEtp(
            @ModelAttribute("etp") @Valid ETPDto etpDto,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherCRUDService.findAll());
            return ETP_FORM;
        }
        TimetableDto timetableDto = timetableCRUDService.makeTimetable(etpDto);
        return redirectTo(Routes.timetable + "/edit/" + timetableDto.getId());
    }
}
