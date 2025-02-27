package ru.darin.non_working_days.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.darin.non_working_days.util.validation.ValidSearchDTO;

@Data
@ValidSearchDTO
@Schema(description = "Класс для передачи количества рабочих дней")
public class CountSearchDTO {
    @Schema(description = "Количество рабочих дней", example = "3")
    private String count;
}
