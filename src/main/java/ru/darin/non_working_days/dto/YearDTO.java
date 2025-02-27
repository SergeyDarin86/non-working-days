package ru.darin.non_working_days.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.darin.non_working_days.util.validation.ValidSearchDTO;

@Data
@ValidSearchDTO
@Schema(description = "Класс для поиска данных по выходным дням за определенный год")
public class YearDTO {
    @Schema(description = "Год", example = "2025")
    private String year;
}
