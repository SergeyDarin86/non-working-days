package ru.darin.non_working_days.util.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandlingControllerAdvice {
    @ExceptionHandler({NonWorkingDaysException.class})
    public ResponseEntity<NonWorkingDaysErrorResponse> handlerException(NonWorkingDaysException e) {
        NonWorkingDaysErrorResponse response = new NonWorkingDaysErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        log.error("Finish method handlerException() for ErrorHandlingControllerAdvice, statusCode is: {}, message is: {} ", HttpStatus.BAD_REQUEST, response.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}