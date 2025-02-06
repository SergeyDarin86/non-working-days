package ru.darin.non_working_days.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class YearResponse {

    @JsonProperty("year")
    private String year;

    @JsonProperty("months")
    private List<Months> months;

}
