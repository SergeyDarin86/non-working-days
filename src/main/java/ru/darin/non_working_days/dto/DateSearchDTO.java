package ru.darin.non_working_days.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import ru.darin.non_working_days.util.validation.ValidSearchDTO;

@ValidSearchDTO
@Schema(description = "Класс для поиска по диапазону дат.")
public class DateSearchDTO {
    @Schema(description = "Нижняя граница диапазона (от)", example = "2025-02-06T18:56:06.898Z")
    private String dateFrom;

    @Schema(description = "Верхняя граница диапазона (до)", example = "2025-02-23T18:56:06.898Z")
    private String dateTo;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    @Override
    public String toString() {
        return "DateSearchDTO{" +
                "dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                '}';
    }
}
