package ru.darin.non_working_days.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Schema(description = "Класс для вывода даты.")
public class DateResponse {
    @Schema(description = "Дата (рабочий день), которая наступит после указанного количества рабочих дней", example = "2025-04-26T18:56:06.898Z")
    private ZonedDateTime dateAfterCountOfWorkingDays;

    public DateResponse(ZonedDateTime dateAfterCountOfWorkingDays) {
        this.dateAfterCountOfWorkingDays = dateAfterCountOfWorkingDays;
    }
}
