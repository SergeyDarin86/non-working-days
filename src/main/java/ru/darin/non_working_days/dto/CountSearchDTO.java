package ru.darin.non_working_days.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.darin.non_working_days.util.validation.ValidSearchDTO;

@ValidSearchDTO
@Schema(description = "Класс для передачи количества рабочих дней")
public class CountSearchDTO {

    @Schema(description = "Количество рабочих дней", example = "3")
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CountSearchDTO{" +
                "count='" + count + '\'' +
                '}';
    }
}
