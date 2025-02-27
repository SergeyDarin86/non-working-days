package ru.darin.non_working_days.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Класс, возвращающий номер месяца и выходные дни для него.")
public class Months {

    @JsonProperty("month")
    @Schema(description = "Номер месяца", example = "1")
    private String month;

    @JsonProperty("days")
    @Schema(description = "Выходные дни для этого месяца", example = "1,2,3,4,5,6,7,8,11,12,18,19,25,26")
    private String days;

}
