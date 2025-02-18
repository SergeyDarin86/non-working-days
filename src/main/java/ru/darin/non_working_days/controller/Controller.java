package ru.darin.non_working_days.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.darin.non_working_days.dto.CountSearchDTO;
import ru.darin.non_working_days.dto.DateSearchDTO;
import ru.darin.non_working_days.service.NonWorkingDaysService;
import ru.darin.non_working_days.util.validation.DateDTOValidator;
import ru.darin.non_working_days.util.exception.ExceptionBuilder;
import ru.darin.non_working_days.util.exception.NonWorkingDaysErrorResponse;
import ru.darin.non_working_days.util.exception.NonWorkingDaysException;

@RestController
public class Controller {
    private final NonWorkingDaysService service;
    private final DateDTOValidator dateDTOValidator;

    public Controller(NonWorkingDaysService service, DateDTOValidator dateDTOValidator) {
        this.service = service;
        this.dateDTOValidator = dateDTOValidator;
    }

    @GetMapping("/showInfo")
    public ResponseEntity getCommonInfoAboutYear(@RequestParam(value = "year") Integer year) {
        return ResponseEntity.ok(service.getCommonResponseForYear(year));
    }

    @PostMapping("/countOfDays")
    public ResponseEntity getCountOfNonWorkingDaysBetweenDays(@RequestBody @Valid DateSearchDTO dateSearchDTO, BindingResult bindingResult) {
        dateDTOValidator.validate(dateSearchDTO, bindingResult);
        ExceptionBuilder.buildErrorMessageForClient(bindingResult);
        return ResponseEntity.ok(service.getCountOfNonWorkingDaysPerPeriod(dateSearchDTO.getDateFrom(), dateSearchDTO.getDateTo()));
    }

    @GetMapping("/dateAfterCount")
    public ResponseEntity getDateAfterCountOfWorkingDays(@RequestBody @Valid CountSearchDTO countSearchDTO, BindingResult bindingResult) {
        ExceptionBuilder.buildErrorMessageForClient(bindingResult);
        return ResponseEntity.ok(service.getDateAfterCountOfWorkingDays(countSearchDTO.getCount()));
    }

    @ExceptionHandler
    private ResponseEntity<NonWorkingDaysErrorResponse> handlerException(NonWorkingDaysException e) {
        NonWorkingDaysErrorResponse response = new NonWorkingDaysErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
