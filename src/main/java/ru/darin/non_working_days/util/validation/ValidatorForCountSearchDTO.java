package ru.darin.non_working_days.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import ru.darin.non_working_days.dto.CountSearchDTO;

@Slf4j
public class ValidatorForCountSearchDTO implements ConstraintValidator<ValidSearchDTO, CountSearchDTO> {
    private static final String REGEX_VALUE = "^[0-9]{1,2}";
    private static final String MSG_NOT_EMPTY = "Значение поля не может быть пустым";
    private static final String MSG_POSITIVE = "Значение поля должно быть больше нуля";

    private static final String MSG_WRONG_FORMAT = "Значение должно содержать только цифры. (Одну или две)";


    @Override
    public boolean isValid(CountSearchDTO dto, ConstraintValidatorContext context) {
        log.info("Start method isValid(CountSearchDto) for ValidatorForCountSearchDTO, CountSearchDto is: {} ", dto);
        boolean isValid = true;
        if (dto.getCount().equals("")) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_NOT_EMPTY)
                    .addPropertyNode("count")
                    .addConstraintViolation();
            isValid = false;
        } else if (!dto.getCount().matches(REGEX_VALUE)) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_WRONG_FORMAT)
                    .addPropertyNode("count")
                    .addConstraintViolation();
            isValid = false;
        } else if (Integer.valueOf(dto.getCount()) <= 0) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_POSITIVE)
                    .addPropertyNode("count")
                    .addConstraintViolation();
            isValid = false;
        }
        return isValid;
    }
}
