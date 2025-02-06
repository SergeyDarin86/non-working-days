package ru.darin.non_working_days.util;

import lombok.Data;

@Data
public class DaysResponse {
    private Byte countOfDays;

    public DaysResponse(Byte countOfDays) {
        this.countOfDays = countOfDays;
    }
}
