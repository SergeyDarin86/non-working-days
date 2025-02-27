package ru.darin.non_working_days.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import ru.darin.non_working_days.dto.YearDTO;

import java.time.ZonedDateTime;

@Slf4j
public class ValidatorForYearDTO implements ConstraintValidator<ValidSearchDTO, YearDTO> {
    private static final String REGEX_VALUE = "^[0-9]{4}";

    private static final String MSG_NOT_EMPTY = "Поле не может быть пустым";

    private static final String MSG_WRONG_FORMAT = "Неверный формат ввода данных. Значение должно содержать 4 цифры в формате: ГГГГ, например (2022)";

    private static final String MSG_WRONG_YEAR = "Данные предоставляются только c 2013 года и до текущего года включительно";

    @Override
    public boolean isValid(YearDTO dto, ConstraintValidatorContext context) {
        log.info("Start method isValid(DateSearchDTO) for ValidatorForYearDTO, YearDTO is: {} ", dto);
        boolean isValid = true;
        if (dto.getYear() == null || dto.getYear().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_NOT_EMPTY)
                    .addPropertyNode("year")
                    .addConstraintViolation();
            isValid = false;
        } else if (!dto.getYear().matches(REGEX_VALUE)) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_WRONG_FORMAT)
                    .addPropertyNode("year")
                    .addConstraintViolation();
            isValid = false;
        } else if (Integer.valueOf(dto.getYear()) > ZonedDateTime.now().getYear() || Integer.valueOf(dto.getYear()) < 2013) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_WRONG_YEAR)
                    .addPropertyNode("year")
                    .addConstraintViolation();
            isValid = false;
        }
        return isValid;
    }
}
