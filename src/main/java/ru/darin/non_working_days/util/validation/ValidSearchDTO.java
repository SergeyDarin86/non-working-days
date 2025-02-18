package ru.darin.non_working_days.util.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidatorForSearchDTO.class, ValidatorForCountSearchDTO.class})
public @interface ValidSearchDTO {
    String message() default "Ошибка валидации";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
