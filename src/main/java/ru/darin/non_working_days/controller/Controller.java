package ru.darin.non_working_days.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.darin.non_working_days.dto.DateSearchDTO;
import ru.darin.non_working_days.service.NonWorkingDaysService;
import ru.darin.non_working_days.util.YearResponse;

@RestController
public class Controller {
    private final NonWorkingDaysService service;

    public Controller(NonWorkingDaysService service) {
        this.service = service;
    }

    @GetMapping("/showInfo")
    public YearResponse getCommonInfoAboutYear(@RequestParam(value = "year") Integer year){
        return service.getCommonResponseForYear(year);
    }

    public ResponseEntity getCountOfNonWorkingDaysBetweenDays(@RequestBody @Valid DateSearchDTO dateSearchDTO, BindingResult bindingResult){
//        dateDTOValidator.validate(dateSearchDTO, bindingResult);
//        ExceptionBuilder.buildErrorMessageForClient(bindingResult);

        return ResponseEntity.ok(service.getCountOfNonWorkingDaysPerPeriod(dateSearchDTO.getDateFrom(), dateSearchDTO.getDateTo()));
    }

}
