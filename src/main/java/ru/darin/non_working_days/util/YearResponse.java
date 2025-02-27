package ru.darin.non_working_days.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Класс, возвращающий данные по выходным дням за выбранный год.")
public class YearResponse {

    @JsonProperty("year")
    @Schema(description = "Год", example = "2025")
    private String year;

    @JsonProperty("months")
    @Schema(description = "Месяца")
    private List<Months> months;

}
