package ru.curriculum.service.curator.validation;

import ru.curriculum.service.curator.dto.CuratorDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, CuratorDTO> {

    public void initialize(PasswordConstraint constraint) {
    }

    public boolean isValid(CuratorDTO curatorDTO, ConstraintValidatorContext context) {
        if (isNewCurator(curatorDTO)) {
            return null != curatorDTO.getPassword() && passwordIsValid(curatorDTO);
        } else {
            return null == curatorDTO.getPassword() || curatorDTO.getPassword().isEmpty() || passwordIsValid(curatorDTO);
        }
    }

    private boolean isNewCurator(CuratorDTO curatorDTO) {
        return null == curatorDTO.getId();
    }

    private boolean passwordIsValid(CuratorDTO curatorDTO) {
        return 3 <= curatorDTO.getPassword().length();
    }
}
