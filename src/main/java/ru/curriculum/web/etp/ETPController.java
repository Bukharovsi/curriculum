package ru.curriculum.web.etp;

import org.h2.result.Row;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.curriculum.service.etp.ETP_DTO;
import ru.curriculum.service.etp.ModuleDTO;
import ru.curriculum.service.etp.SectionDTO;
import ru.curriculum.web.View;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/etp")
public class ETPController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        ETP_DTO etp = new ETP_DTO();
        etp.setId(1);
        etp.setName("Модуль");
        ModuleDTO moduleDTO = new ModuleDTO();
        moduleDTO.setTitle("Название раздела");
        etp.getModules().add(moduleDTO);
        model.addAttribute("etp", etp);

        return "etp/index";
    }

    @RequestMapping(params={"addModule"}, method = RequestMethod.POST)
    public String addModule(final @ModelAttribute("etp") ETP_DTO etp_dto, final BindingResult bindingResult) {
        etp_dto.getModules().add(new ModuleDTO());

        return "etp/index";
    }

    @RequestMapping(params = {"removeModule"}, method = RequestMethod.POST)
    public String removeModule(
            final @ModelAttribute("etp") ETP_DTO etp,
            final BindingResult bindingResult,
            final HttpServletRequest req
    ) {
        Integer indexOfModule = Integer.valueOf(req.getParameter("removeModule"));
        etp.getModules().remove(indexOfModule.intValue());

        return "etp/index";
    }

    @RequestMapping(params = {"addSection"}, method = RequestMethod.POST)
    public String addSectionToModule(
            final @ModelAttribute("etp") ETP_DTO etp,
            final BindingResult bindingResult,
            final HttpServletRequest req
    ) {
        Integer indexOfSectionInModule = Integer.valueOf(req.getParameter("addSection"));
        ModuleDTO moduleDTO = etp.getModules().get(indexOfSectionInModule.intValue());
        moduleDTO.getSections().add(new SectionDTO());

        return "etp/index";
    }

    @RequestMapping(params = {"removeSection"}, method = RequestMethod.POST)
    public String removeSection(
            final @ModelAttribute("etp") ETP_DTO etp,
            final BindingResult bindingResult,
            final HttpServletRequest req
    ) {
        String indexOfSectionInModuleAsString = req.getParameter("removeSection");
        String[] indexOfSectionInModule = indexOfSectionInModuleAsString.split("\\.");
        Integer indexOfModule = Integer.valueOf(indexOfSectionInModule[0]);
        Integer indexOfSection = Integer.valueOf(indexOfSectionInModule[1]);

        etp.getModules().get(indexOfModule.intValue()).getSections().remove(indexOfSection.intValue());

        return "etp/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(@RequestParam ETP_DTO etp) {

        return View.INDEX;
    }
}
