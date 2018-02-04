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
import ru.curriculum.service.curator.CuratorCRUDService;
import ru.curriculum.service.curator.dto.CuratorDto;
import ru.curriculum.service.curator.validation.UniqueLoginValidator;
import ru.curriculum.web.View;

import javax.validation.Valid;

import static ru.curriculum.web.Redirect.redirectTo;

@Controller
@RequestMapping(path = Routes.curator)
public class CuratorManagementController {
    @Autowired
    private CuratorCRUDService curatorCRUDService;
    @Autowired
    private UniqueLoginValidator uniqueLoginValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String curatorList(Model model) {
        model.addAttribute("curators", curatorCRUDService.findAllCurators());

        return View.CURATOR_LIST;
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getNewCuratorForm(Model model) {
        model.addAttribute("curator", new CuratorDto());

        return View.CURATOR_FORM;
    }

    @RequestMapping(value = "/edit/{id}")
    public String getEditCuratorForm(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("curator", curatorCRUDService.getCurator(id));

        return View.CURATOR_FORM;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createCurator(@ModelAttribute("curator") @Valid CuratorDto curatorDto, BindingResult errors) {
        uniqueLoginValidator.validate(curatorDto, errors);
        if(errors.hasErrors()) {
            return View.CURATOR_FORM;
        }
        curatorCRUDService.create(curatorDto);

        return redirectTo(Routes.curator);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public String updateCurator(@ModelAttribute("curator") @Valid CuratorDto curatorDto, BindingResult errors) {
        if(errors.hasErrors()) {
            return View.CURATOR_FORM;
        }
        curatorCRUDService.update(curatorDto);

        return redirectTo(Routes.curator);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCurator(@PathVariable("id") Integer id) {
        curatorCRUDService.delete(id);

        return redirectTo(Routes.curator);
    }
}
