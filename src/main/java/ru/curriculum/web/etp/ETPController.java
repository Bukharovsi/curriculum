package ru.curriculum.web.etp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.etp.service.ETPFromStateProgramFormationService;
import ru.curriculum.service.etp.service.ETP_CRUDService;
import ru.curriculum.service.etp.dto.*;
import ru.curriculum.service.etp.statusManager.ETPStatusManager;
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
    @Autowired
    private ETPFromStateProgramFormationService etpFromStateProgramFormationService;
    @Autowired
    private ETPStatusManager etpStatusManager;

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String getETPForm(Model model) {
        model.addAttribute("etp", new ETPDto());
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvaliableStatusesForNewEtp());

        return ETP_FORM;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String getEditForm(@PathVariable("id") Integer etpId, Model model) {
        ETPDto etpDto = etpCRUDService.get(etpId);
        model.addAttribute("etp", etpDto);
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etpDto.getActualStatus()));

        return ETP_FORM;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("etps", etpCRUDService.getAll());

        return ETP_LIST;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(
            @ModelAttribute("etp") @Valid ETPDto etp,
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
            @ModelAttribute("etp") @Valid ETPDto etp,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherCRUDService.findAll());
            model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));

            return ETP_FORM;
        }
        etpCRUDService.update(etp);

        return redirectTo(Routes.etp);
    }

    @RequestMapping(value = "/etpTemplate/{stateProgramId}", method = RequestMethod.GET)
    public String getEtpTemplateBaseOnStateProgram(
            @PathVariable("stateProgramId") Integer stateProgramId,
            Model model
    ) {
        ETPDto etpDto = etpFromStateProgramFormationService.formETPTemplate(stateProgramId);
        model.addAttribute("etp", etpDto);
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etpDto.getActualStatus()));

        return ETP_FORM;
    }

    @RequestMapping(value = "/etpFormedByStateProgram/{stateProgramId}", method = RequestMethod.GET)
    public String getEtpByStateProgramCreation(
            @PathVariable("stateProgramId") Integer stateProgramId,
            Model model
    ) {
        ETPDto etpDto = etpCRUDService.getByStateProgramId(stateProgramId);
        model.addAttribute("etp", etpDto);
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etpDto.getActualStatus()));

        return ETP_FORM;
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer eptId) {
        etpCRUDService.delete(eptId);

        return redirectTo(Routes.etp);
    }

    @RequestMapping(params = {"addEMAModule"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String addEMAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        etp.getEmaModules().add(new EMAModuleDto());

        return ETP_FORM;
    }

    @RequestMapping(params = {"removeEMAModule"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeEMAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        Integer moduleIndex = Integer.valueOf(req.getParameter("removeEMAModule"));
        etp.getEmaModules().remove(moduleIndex.intValue());

        return ETP_FORM;
    }

    @RequestMapping(params = {"addOMAModule"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String addOMAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        etp.getOmaModules().add(new OMAModuleDto());

        return ETP_FORM;
    }

    @RequestMapping(params = {"removeOMAModule"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeOMAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        Integer moduleIndex = Integer.valueOf(req.getParameter("removeOMAModule"));
        etp.getOmaModules().remove(moduleIndex.intValue());

        return ETP_FORM;
    }

    @RequestMapping(params={"addEAModule"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String addEAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        etp.getEaModules().add(new EAModuleDto());

        return ETP_FORM;
    }

    @RequestMapping(params = {"removeEAModule"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeEAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        Integer indexOfModule = Integer.valueOf(req.getParameter("removeEAModule"));
        etp.getEaModules().remove(indexOfModule.intValue());

        return ETP_FORM;
    }

    @RequestMapping(params = {"addEASection"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String addEASection(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        Integer indexOfSectionInModule = Integer.valueOf(req.getParameter("addEASection"));
        EAModuleDto moduleDTO = etp.getEaModules().get(indexOfSectionInModule.intValue());
        moduleDTO.getSections().add(new EASectionDto());

        return ETP_FORM;
    }

    @RequestMapping(params = {"removeEASection"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeEASection(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        String indexOfSectionInModuleAsString = req.getParameter("removeEASection");
        String[] indexOfSectionInModule = indexOfSectionInModuleAsString.split("\\.");
        Integer indexOfModule = Integer.valueOf(indexOfSectionInModule[0]);
        Integer indexOfSection = Integer.valueOf(indexOfSectionInModule[1]);

        etp.getEaModules().get(indexOfModule.intValue()).getSections().remove(indexOfSection.intValue());

        return ETP_FORM;
    }

    @RequestMapping(params = {"addEATopic"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String addEATopic(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        String[] pathToTopic = req.getParameter("addEATopic").split("\\.");
        Integer moduleIndex = Integer.valueOf(pathToTopic[0]);
        Integer sectionIndex = Integer.valueOf(pathToTopic[1]);

        etp
                .getEaModules().get(moduleIndex)
                .getSections().get(sectionIndex)
                .getTopics()
                .add(new EATopicDto());

        return ETP_FORM;
    }

    @RequestMapping(params = {"removeEATopic"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeEATopic(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));
        String[] pathToTopic = req.getParameter("removeEATopic").split("\\.");
        Integer moduleIndex = Integer.valueOf(pathToTopic[0]);
        Integer sectionIndex = Integer.valueOf(pathToTopic[1]);
        Integer topicIndex = Integer.valueOf(pathToTopic[2]);

        etp
                .getEaModules().get(moduleIndex)
                .getSections().get(sectionIndex)
                .getTopics()
                .remove(topicIndex.intValue());

        return ETP_FORM;
    }
}
