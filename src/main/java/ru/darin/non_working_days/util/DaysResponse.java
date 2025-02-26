package ru.darin.non_working_days.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Класс для вывода количества выходных дней, которые находятся в диапазоне дат")
public class DaysResponse {

    @Schema(description = "Количество выходных дней", example = "10")
    private Byte countOfDays;

    public DaysResponse(Byte countOfDays) {
        this.countOfDays = countOfDays;
    }
}
