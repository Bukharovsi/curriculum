package ru.curriculum.web.etp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.etp.controller.IRowController;
import ru.curriculum.service.etp.controller.OrderedRowController;
import ru.curriculum.service.etp.controller.RowController;
import ru.curriculum.service.etp.dto.*;
import ru.curriculum.service.etp.service.ETPCommentCRUDService;
import ru.curriculum.service.etp.service.ETPFromStateProgramFormationService;
import ru.curriculum.service.etp.service.ETP_CRUDService;
import ru.curriculum.service.etp.statusManager.ETPStatusManager;
import ru.curriculum.service.teacher.TeacherCRUDService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import static ru.curriculum.web.Redirect.redirectTo;
import static ru.curriculum.web.View.ETP_FORM;
import static ru.curriculum.web.View.ETP_LIST;

@Controller
@RequestMapping(path = Routes.etp)
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
public class ETPController {
    @Autowired
    private ETP_CRUDService etpCRUDService;
    @Autowired
    private TeacherCRUDService teacherCRUDService;
    @Autowired
    private ETPFromStateProgramFormationService etpFromStateProgramFormationService;
    @Autowired
    private ETPStatusManager etpStatusManager;
    @Autowired
    private ETPCommentCRUDService etpCommentCRUDService;

    private IRowController rowController = new OrderedRowController(new RowController());

    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String getETPForm(Model model) {
        model.addAttribute("etp", new ETPDto());
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatusesForNewEtp());

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
    public String getEtpTemplateBasedOnStateProgram(
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
    public String getEtpByStateProgramIdCreatedBy(
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

    @RequestMapping(value = "/changeStatus", method = {RequestMethod.POST, RequestMethod.PUT})
    public String moveEtpToNewStatus(
            final @ModelAttribute("etp") @Valid ETPDto etpDto,
            final BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("teachers", teacherCRUDService.findAll());
            model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etpDto.getActualStatus()));

            return ETP_FORM;
        }
        etpCRUDService.changeStatus(etpDto);

        return redirectTo(Routes.etp + "/edit/" + etpDto.getId());
    }

    @RequestMapping(params = {"addEMAModule"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String addEMAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));

        Integer rowNumber = Integer.valueOf(req.getParameter("addEMAModule"));
        rowController.add(new EMAModuleDto(rowNumber), etp.getEmaModules());

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

        Integer rowNumber = Integer.valueOf(req.getParameter("removeEMAModule"));
        rowController.remove(rowNumber, etp.getEmaModules());

        return ETP_FORM;
    }

    @RequestMapping(params = {"addOMAModule"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String addOMAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));

        Integer rowNumber = Integer.valueOf(req.getParameter("addOMAModule"));
        rowController.add(new OMAModuleDto(rowNumber), etp.getOmaModules());

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

        Integer rowNumber = Integer.valueOf(req.getParameter("removeOMAModule"));
        rowController.remove(rowNumber, etp.getOmaModules());

        return ETP_FORM;
    }

    @RequestMapping(params={"addEAModule"}, method = {RequestMethod.POST, RequestMethod.PUT})
    public String addEAModule(
            final @ModelAttribute("etp") @Valid ETPDto etp,
            final BindingResult bindingResult,
            Model model,
            final HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etp.getActualStatus()));

        Integer rowNumber = Integer.valueOf(req.getParameter("addEAModule"));
        rowController.add(new EAModuleDto(rowNumber), etp.getEaModules());

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

        Integer rowNumber = Integer.valueOf(req.getParameter("removeEAModule"));
        rowController.remove(rowNumber, etp.getEaModules());

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

        String indexOfSectionInModuleAsString = req.getParameter("addEASection");
        String[] indexOfSectionInModule = indexOfSectionInModuleAsString.split("\\.");
        Integer indexOfModule = Integer.valueOf(indexOfSectionInModule[0]);
        Integer rowNumber = Integer.valueOf(indexOfSectionInModule[1]);

        rowController.add(new EASectionDto(rowNumber), etp.getEaModules().get(indexOfModule).getSections());

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
        Integer rowNumber = Integer.valueOf(indexOfSectionInModule[1]);

        rowController.remove(rowNumber, etp.getEaModules().get(indexOfModule).getSections());

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
        Integer rowNumber = Integer.valueOf(pathToTopic[2]);

        List<EATopicDto> topics = etp
                .getEaModules().get(moduleIndex)
                .getSections().get(sectionIndex)
                .getTopics();

        rowController.add(new EATopicDto(rowNumber), topics);

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
        Integer rowNumber = Integer.valueOf(pathToTopic[2]);

        List<EATopicDto> topics = etp
                .getEaModules().get(moduleIndex)
                .getSections().get(sectionIndex)
                .getTopics();

        rowController.remove(rowNumber, topics);

        return ETP_FORM;
    }

    @RequestMapping(value = "/comment/new", method = {RequestMethod.POST, RequestMethod.PUT})
    public String addNewComment(@ModelAttribute("etp") ETPDto etpDto, Model model) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etpDto.getActualStatus()));
        etpDto.getComments().add(new CommentDto());

        return ETP_FORM;
    }

    @RequestMapping(value = "/comment/remove", method = {RequestMethod.POST, RequestMethod.PUT})
    public String removeCommentFromCommentList(
            @ModelAttribute("etp") ETPDto etpDto,
            Model model,
            HttpServletRequest req
    ) {
        model.addAttribute("teachers", teacherCRUDService.findAll());
        model.addAttribute("availableStatuses", etpStatusManager.getAvailableStatuses(etpDto.getActualStatus()));
        Integer indexOfComment = Integer.valueOf(req.getParameter("removeComment"));
        etpDto.getComments().remove(indexOfComment.intValue());

        return ETP_FORM;
    }

    @RequestMapping(value = "comment/create")
    public String createComment(
            @ModelAttribute("comment") @Valid ETPDto etpDto,
            HttpServletRequest req
    ) {
        Integer indexOfNewComment = Integer.valueOf(req.getParameter("createComment"));
        etpCommentCRUDService.create(etpDto.getId(), etpDto.getComments().get(indexOfNewComment.intValue()));

        return redirectTo(Routes.etp + "/edit/" + etpDto.getId());
    }

    @RequestMapping(value = "/{etpId}/comment/delete/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable("etpId") Integer etpId, @PathVariable("id") Integer id) {
        etpCommentCRUDService.deleteEtpComment(id);
        return redirectTo(Routes.etp + "/edit/" + etpId);
    }
}
