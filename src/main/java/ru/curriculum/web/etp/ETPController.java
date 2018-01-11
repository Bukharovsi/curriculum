package ru.curriculum.web.etp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.etp.ETP_CRUDService;
import ru.curriculum.service.etp.dto.ETP_DTO;
import ru.curriculum.service.etp.dto.EAModuleDTO;
import ru.curriculum.service.etp.dto.EASectionDTO;
import ru.curriculum.service.teacher.TeacherCRUDService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static ru.curriculum.web.Redirect.*;
import static ru.curriculum.web.View.*;

@Controller
@RequestMapping(path = Routes.etp)
public class ETPController {
    @Autowired
    private ETP_CRUDService etpCRUDService;
    @Autowired
    private TeacherCRUDService teacherCRUDService;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("etps", etpCRUDService.getAll());

        return ETP_LIST;
    }

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String getETPForm(Model model) {
        model.addAttribute("etp", new ETP_DTO());
        model.addAttribute("teachers", teacherCRUDService.findAll());

        return ETP_FORM;
    }

    @RequestMapping(params={"addModule"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String addModule(
            final @ModelAttribute("etp") @Valid ETP_DTO etp,
            final BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        etp.getEaModules().add(new EAModuleDTO());

        return ETP_FORM;
    }

    @RequestMapping(params = {"removeModule"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeModule(
            final @ModelAttribute("etp") @Valid ETP_DTO etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        Integer indexOfModule = Integer.valueOf(req.getParameter("removeModule"));
        etp.getEaModules().remove(indexOfModule.intValue());

        return ETP_FORM;
    }

    @RequestMapping(params = {"addSection"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String addSectionToModule(
            final @ModelAttribute("etp") @Valid ETP_DTO etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        Integer indexOfSectionInModule = Integer.valueOf(req.getParameter("addSection"));
        EAModuleDTO moduleDTO = etp.getEaModules().get(indexOfSectionInModule.intValue());
        moduleDTO.getSections().add(new EASectionDTO());

        return ETP_FORM;
    }

    @RequestMapping(params = {"removeSection"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeSection(
            final @ModelAttribute("etp") @Valid ETP_DTO etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        String indexOfSectionInModuleAsString = req.getParameter("removeSection");
        String[] indexOfSectionInModule = indexOfSectionInModuleAsString.split("\\.");
        Integer indexOfModule = Integer.valueOf(indexOfSectionInModule[0]);
        Integer indexOfSection = Integer.valueOf(indexOfSectionInModule[1]);

        etp.getEaModules().get(indexOfModule.intValue()).getSections().remove(indexOfSection.intValue());

        return ETP_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(
            @ModelAttribute("etp") @Valid ETP_DTO etp,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherCRUDService.findAll());

            return ETP_FORM;
        }
        etpCRUDService.create(etp);

        return redirectTo(Routes.etp);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String update(
            @ModelAttribute("etp") @Valid ETP_DTO etp,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherCRUDService.findAll());

            return ETP_FORM;
        }
        etpCRUDService.update(etp);

        return redirectTo(Routes.etp);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Integer etpId, Model model) {
        model.addAttribute("etp", etpCRUDService.get(etpId));
        model.addAttribute("teachers", teacherCRUDService.findAll());

        return ETP_FORM;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer eptId) {
        etpCRUDService.delete(eptId);

        return redirectTo(Routes.etp);
    }
}
