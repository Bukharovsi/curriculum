package ru.curriculum.service.curator.validation;

import ru.curriculum.service.curator.dto.CuratorDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, CuratorDto> {

    public void initialize(PasswordConstraint constraint) {
    }

    public boolean isValid(CuratorDto curatorDto, ConstraintValidatorContext context) {
        if (isNewCurator(curatorDto)) {
            return null != curatorDto.getPassword() && passwordIsValid(curatorDto);
        } else {
            return null == curatorDto.getPassword() || curatorDto.getPassword().isEmpty() || passwordIsValid(curatorDto);
        }
    }

    private boolean isNewCurator(CuratorDto curatorDto) {
        return null == curatorDto.getId();
    }

    private boolean passwordIsValid(CuratorDto curatorDto) {
        return 3 <= curatorDto.getPassword().length();
    }
}
