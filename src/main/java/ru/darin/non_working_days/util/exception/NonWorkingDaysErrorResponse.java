package ru.darin.non_working_days.util.exception;

import lombok.Data;

@Data
public class NonWorkingDaysErrorResponse {
    private String message;

    private long timestamp;

    public NonWorkingDaysErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
