package ru.darin.non_working_days.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.darin.non_working_days.dto.CountSearchDTO;
import ru.darin.non_working_days.dto.DateSearchDTO;
import ru.darin.non_working_days.dto.YearDTO;
import ru.darin.non_working_days.resource.NonWorkingDaysResource;
import ru.darin.non_working_days.service.NonWorkingDaysService;
import ru.darin.non_working_days.util.exception.ExceptionBuilder;
import ru.darin.non_working_days.util.exception.NonWorkingDaysErrorResponse;
import ru.darin.non_working_days.util.exception.NonWorkingDaysException;

@Slf4j
@RestController
public class Controller implements NonWorkingDaysResource {
    private final NonWorkingDaysService service;

    public Controller(NonWorkingDaysService service) {
        this.service = service;
    }

    @PostMapping("/showInfo")
    public ResponseEntity getCommonInfoAboutYear(@RequestBody @Valid YearDTO yearDTO, BindingResult bindingResult) {
        ExceptionBuilder.buildErrorMessageForClient(bindingResult);
        return ResponseEntity.ok(service.getCommonResponseForYear(yearDTO.getYear()));
    }

    @PostMapping("/countOfDays")
    public ResponseEntity getCountOfNonWorkingDaysBetweenDays(@RequestBody @Valid DateSearchDTO dateSearchDTO, BindingResult bindingResult) {
        ExceptionBuilder.buildErrorMessageForClient(bindingResult);
        return ResponseEntity.ok(service.getCountOfNonWorkingDaysPerPeriod(dateSearchDTO.getDateFrom(), dateSearchDTO.getDateTo()));
    }

    @PostMapping("/dateAfterCount")
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
        log.error("Finish method handlerException() for Controller, statusCode is: {}, message is: {} ", HttpStatus.BAD_REQUEST, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
