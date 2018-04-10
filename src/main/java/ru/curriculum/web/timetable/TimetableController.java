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
import ru.curriculum.service.timetable.AddressFindingService;
import ru.curriculum.service.timetable.LessonFormFindingService;
import ru.curriculum.service.timetable.TimetableCRUDService;
import ru.curriculum.service.timetable.TimetableFindingService;
import ru.curriculum.service.timetable.dto.LessonDto;
import ru.curriculum.service.timetable.dto.TimetableDto;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private LessonFormFindingService lessonFormFindingService;
    @Autowired
    private AddressFindingService addressFindingService;
    @Autowired
    private TimetableFindingService timetableFindingService;

    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model model) {
        model.addAttribute("timetables", timetableCRUDService.findAll());
        return TIMETABLE_LIST;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditForm(@PathVariable("id") Integer id, Model model) {
        TimetableDto timetableDto = timetableCRUDService.get(id);
        model.addAttribute("timetable", timetableDto);
        model.addAttribute("lessonFormList", lessonFormFindingService.findAll());
        model.addAttribute("addressList", addressFindingService.getAddresses());
        model.addAttribute("lessonThemes", timetableFindingService.findLessonThemesAllByEtpId(timetableDto.getCreateFromEtpId()));
        model.addAttribute("teachers", teacherCRUDService.findAll());
        return TIMETABLE_FORM;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public String editTimetable(
            @ModelAttribute("timetable") @Valid TimetableDto timetableDto,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("lessonFormList", lessonFormFindingService.findAll());
            model.addAttribute("addressList", addressFindingService.getAddresses());
            model.addAttribute("lessonThemes", timetableFindingService.findLessonThemesAllByEtpId(timetableDto.getCreateFromEtpId()));
            model.addAttribute("teachers", teacherCRUDService.findAll());
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
        timetableCRUDService.delete(id);
        return redirectTo(Routes.timetable);
    }

    @RequestMapping(params = {"addLesson"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String addLesson(
            final @ModelAttribute("timetable") @Valid TimetableDto timetableDto,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req

    ) {
        model.addAttribute("lessonFormList", lessonFormFindingService.findAll());
        model.addAttribute("addressList", addressFindingService.getAddresses());
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("lessonThemes", timetableFindingService.findLessonThemesAllByEtpId(timetableDto.getCreateFromEtpId()));
        Integer schoolDayIndex = Integer.valueOf(req.getParameter("addLesson"));
        timetableDto
                .getSchoolDays().get(schoolDayIndex.intValue())
                .getLessons()
                .add(new LessonDto());
        return TIMETABLE_FORM;
    }

    @RequestMapping(params = {"removeLesson"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String removeLesson(
            final @ModelAttribute("timetable") @Valid TimetableDto timetableDto,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req

    ) {
        model.addAttribute("lessonFormList", lessonFormFindingService.findAll());
        model.addAttribute("addressList", addressFindingService.getAddresses());
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("lessonThemes", timetableFindingService.findLessonThemesAllByEtpId(timetableDto.getCreateFromEtpId()));
        String indexOfLessonInSchoolDaysAsString = req.getParameter("removeLesson");
        String[] indexOfLessonInSchoolDays = indexOfLessonInSchoolDaysAsString.split("\\.");
        Integer indexOfSchoolDay = Integer.valueOf(indexOfLessonInSchoolDays[0]);
        Integer indexOfLesson = Integer.valueOf(indexOfLessonInSchoolDays[1]);
        timetableDto
                .getSchoolDays().get(indexOfSchoolDay.intValue())
                .getLessons()
                .remove(indexOfLesson.intValue());
        return TIMETABLE_FORM;
    }
}
