package ru.curriculum.service.etp.validation;

import org.springframework.beans.BeanWrapperImpl;
import ru.curriculum.service.etp.dto.ETPDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class ETPDatesValidator implements ConstraintValidator<EndDateLargerThanBeginDateConstraint, ETPDto> {

    private String beginDateFiled;
    private String endDateField;

    @Override
    public void initialize(EndDateLargerThanBeginDateConstraint constraintAnnotation) {
        this.beginDateFiled = constraintAnnotation.beginDate();
        this.endDateField = constraintAnnotation.endDate();
    }

    @Override
    public boolean isValid(ETPDto etp, ConstraintValidatorContext context) {
        Date beginDate = getFieldValue(beginDateFiled, etp);
        Date endDate = getFieldValue(endDateField, etp);

        if(null != beginDate && endDate != null && beginDate.after(endDate)) {
            return false;
        }

        return true;
    }

    private Date getFieldValue(String beginDateFiled, ETPDto etp) {
        return (Date) new BeanWrapperImpl(etp).getPropertyValue(beginDateFiled);
    }
}
