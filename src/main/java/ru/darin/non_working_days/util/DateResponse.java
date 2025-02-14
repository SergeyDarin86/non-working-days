package ru.darin.non_working_days.util;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class DateResponse {
    private ZonedDateTime dateAfterCountOfWorkingDays;

    public DateResponse(ZonedDateTime dateAfterCountOfWorkingDays) {
        this.dateAfterCountOfWorkingDays = dateAfterCountOfWorkingDays;
    }
}
