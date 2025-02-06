package ru.darin.non_working_days.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Months {

    @JsonProperty("month")
    private String month;

    @JsonProperty("days")
    private String days;

}
