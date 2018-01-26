package ru.curriculum.service.curator.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.service.curator.dto.CuratorDTO;

// TODO: запихать в одно место с валидацией формы CuratorDTO
@Component
public class UniqueLoginValidator {
    @Autowired
    private CuratorRepository curatorRepository;

    public void validate(CuratorDTO curatorDTO, BindingResult errors) {
        if(null != curatorRepository.findByLogin(curatorDTO.getUsername())) {
            errors.addError(new ObjectError("username", "Такой логин уже существует"));
        }
    }
}
