package ru.curriculum.web.stateSchedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.curriculum.application.route.Routes;
import ru.curriculum.service.curator.CuratorCRUDService;
import ru.curriculum.service.division.DivisionFindingService;
import ru.curriculum.service.stateSchedule.dto.InternshipDto;
import ru.curriculum.service.stateSchedule.dto.StateProgramCreationDto;
import ru.curriculum.service.stateSchedule.service.ImplementationFormFindService;
import ru.curriculum.service.stateSchedule.service.StateScheduleCRUDService;
import ru.curriculum.service.stateSchedule.service.StateScheduleCreationFromFileService;
import ru.curriculum.service.stateSchedule.service.StudyModeFindService;
import ru.curriculum.web.View;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static ru.curriculum.web.Redirect.redirectTo;

@Controller
@RequestMapping(path = Routes.stateSchedule)
public class StateScheduleController {

    @Autowired
    private StateScheduleCRUDService stateScheduleCRUDService;

    @Autowired
    private ImplementationFormFindService implementationFormFindService;

    @Autowired
    private StudyModeFindService studyModeFindService;

    @Autowired
    private CuratorCRUDService curatorCRUDService;

    @Autowired
    protected DivisionFindingService divisionService;

    @Autowired
    private StateScheduleCreationFromFileService stateScheduleCreationFromFileService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("stateSchedule", stateScheduleCRUDService.findAll());
        return View.STATE_SCHEDULE_LIST;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/newForm")
    public String newForm(Model model) {
        model.addAttribute("stateProgram", new StateProgramCreationDto());
        model.addAttribute("implementationFormList", implementationFormFindService.findAll());
        model.addAttribute("studyModeList", studyModeFindService.findAll());
        model.addAttribute("curatorList", curatorCRUDService.findAllCurators());
        model.addAttribute("divisionList", divisionService.findAll());
        return View.STATE_SCHEDULE_FORM;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/editForm/{id}")
    public String editForm(Model model, @PathVariable("id") Integer stateProgramId) {
        StateProgramCreationDto stateProgramViewDto = stateScheduleCRUDService.getCreationDto(stateProgramId);
        model.addAttribute("stateProgram", stateProgramViewDto);
        model.addAttribute("implementationFormList", implementationFormFindService.findAll());
        model.addAttribute("studyModeList", studyModeFindService.findAll());
        model.addAttribute("curatorList", curatorCRUDService.findAllCurators());
        model.addAttribute("divisionList", divisionService.findAll());
        return View.STATE_SCHEDULE_FORM;
    }


    @RequestMapping(method = RequestMethod.POST, path = "/edit")
    public String addNewStateProgram(
            @ModelAttribute("stateProgram") @Valid StateProgramCreationDto stateProgramDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("implementationFormList", implementationFormFindService.findAll());
            model.addAttribute("studyModeList", studyModeFindService.findAll());
            model.addAttribute("curatorList", curatorCRUDService.findAllCurators());
            model.addAttribute("divisionList", divisionService.findAll());
            return View.STATE_SCHEDULE_FORM;
        }

        stateScheduleCRUDService.create(stateProgramDto);
        return redirectTo(Routes.stateSchedule);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/edit")
    public String editStateProgram(
            @ModelAttribute("stateProgram") @Valid StateProgramCreationDto stateProgramDto,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("implementationFormList", implementationFormFindService.findAll());
            model.addAttribute("studyModeList", studyModeFindService.findAll());
            model.addAttribute("curatorList", curatorCRUDService.findAllCurators());
            model.addAttribute("divisionList", divisionService.findAll());
            return View.STATE_SCHEDULE_FORM;
        }
        stateScheduleCRUDService.create(stateProgramDto);
        return redirectTo(Routes.stateSchedule);
    }

    @RequestMapping(path = "/delete/{id}")
    public String delete(
            @PathVariable("id") Integer id
    ) {
        stateScheduleCRUDService.delete(id);
        return redirectTo(Routes.stateSchedule);
    }

    @RequestMapping(path = "/clean")
    public String clean() {
        stateScheduleCRUDService.clean();
        return redirectTo(Routes.stateSchedule);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/uploadStateProgram")
    public String uploadStateProgramFile(@RequestParam("file") MultipartFile file) {
        stateScheduleCreationFromFileService.makeStateScheduleTemplatesFromFile(file);
        return redirectTo(Routes.stateSchedule);
    }

    @RequestMapping(params = {"addInternship"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String addInternship(
            @ModelAttribute("stateProgram") StateProgramCreationDto stateProgramDto,
            BindingResult bindingResult,
            Model model
    ) {
        model.addAttribute("implementationFormList", implementationFormFindService.findAll());
        model.addAttribute("studyModeList", studyModeFindService.findAll());
        model.addAttribute("curatorList", curatorCRUDService.findAllCurators());
        model.addAttribute("divisionList", divisionService.findAll());

//        etp.getOmaModules().add(new OMAModuleDto());
        stateProgramDto.getInternships().add(new InternshipDto());

        return View.STATE_SCHEDULE_FORM;
    }

    @RequestMapping(params = {"removeInternship"}, method = {RequestMethod.PUT, RequestMethod.POST})
    public String removeInternship(
            @ModelAttribute("stateProgram") StateProgramCreationDto stateProgramDto,
            BindingResult bindingResult,
            Model model,
            HttpServletRequest req
    ) {
        model.addAttribute("implementationFormList", implementationFormFindService.findAll());
        model.addAttribute("studyModeList", studyModeFindService.findAll());
        model.addAttribute("curatorList", curatorCRUDService.findAllCurators());
        model.addAttribute("divisionList", divisionService.findAll());

        int internshipIndex = Integer.valueOf(req.getParameter("removeInternship"));
        stateProgramDto.getInternships().remove(internshipIndex);

        return View.STATE_SCHEDULE_FORM;
    }
}
