package ru.darin.non_working_days.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;
import ru.darin.non_working_days.dto.DateSearchDTO;

import java.time.ZonedDateTime;

@Slf4j
public class ValidatorForSearchDTO implements ConstraintValidator<ValidSearchDTO, DateSearchDTO> {

    private static final String REGEX_VALUE = "^[0-9]{4}-[0-1][0-9]-[0-9]{2}T[0-2][0-9]:[0-5][0-9]:[0-5][0-9]Z$";

    private static final String MSG_NOT_EMPTY = "Поле не может быть пустым";

    private static final String MSG_WRONG_FORMAT = "Неверный формат ввода даты. Введите дату в формате: ГГГГ-ММ-ДДTчч:мм:ссZ (2022-12-03T23:12:28Z)";

    private static final String MSG_WRONG_DATES_ORDER = "dateFrom должна быть до dateTo";


    @Override
    public boolean isValid(DateSearchDTO dto, ConstraintValidatorContext context) {
        log.info("Start method isValid(DateSearchDTO) for ValidatorForSearchDTO, DateSearchDTO is: {} ", dto);
        boolean isValid = true;
        if (dto.getDateFrom() == null || dto.getDateFrom().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_NOT_EMPTY)
                    .addPropertyNode("dateFrom")
                    .addConstraintViolation();
            isValid = false;
        } else if (dto.getDateTo() == null || dto.getDateTo().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_NOT_EMPTY)
                    .addPropertyNode("dateTo")
                    .addConstraintViolation();
            isValid = false;
        } else if (!dto.getDateFrom().matches(REGEX_VALUE)) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_WRONG_FORMAT)
                    .addPropertyNode("dateFrom")
                    .addConstraintViolation();
            isValid = false;
        } else if (!dto.getDateTo().matches(REGEX_VALUE)) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_WRONG_FORMAT)
                    .addPropertyNode("dateTo")
                    .addConstraintViolation();
            isValid = false;
        } else if (ZonedDateTime.parse(dto.getDateFrom()).isAfter(ZonedDateTime.parse(dto.getDateTo())) || dto.getDateFrom().equals(dto.getDateTo())) {
            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(MSG_WRONG_DATES_ORDER)
                    .addPropertyNode("dateFrom")
                    .addConstraintViolation();
            isValid = false;
        }
        return isValid;
    }
}
