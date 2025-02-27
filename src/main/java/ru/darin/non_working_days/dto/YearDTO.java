package ru.darin.non_working_days.dto;

import lombok.Data;
import ru.darin.non_working_days.util.validation.ValidSearchDTO;

@Data
@ValidSearchDTO
public class YearDTO {
    private String year;
}
