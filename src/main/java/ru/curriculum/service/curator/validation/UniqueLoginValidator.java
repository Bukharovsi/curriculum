package ru.curriculum.service.curator.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import ru.curriculum.domain.admin.curator.repository.CuratorRepository;
import ru.curriculum.service.curator.dto.CuratorDto;

// TODO: запихать в одно место с валидацией формы CuratorDto
@Component
public class UniqueLoginValidator {
    @Autowired
    private CuratorRepository curatorRepository;

    public void validate(CuratorDto curatorDto, BindingResult errors) {
        if(null != curatorRepository.findByLogin(curatorDto.getUsername())) {
            errors.addError(new ObjectError("username", "Такой логин уже существует"));
        }
    }
}
