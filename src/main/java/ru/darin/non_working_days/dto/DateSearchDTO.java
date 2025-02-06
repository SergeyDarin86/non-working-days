package ru.darin.non_working_days.dto;

import ru.darin.non_working_days.util.validation.ValidSearchDTO;

@ValidSearchDTO
public class DateSearchDTO {
    private String dateFrom;

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
