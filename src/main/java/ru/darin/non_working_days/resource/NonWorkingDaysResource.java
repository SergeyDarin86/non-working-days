package ru.darin.non_working_days.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.darin.non_working_days.dto.CountSearchDTO;
import ru.darin.non_working_days.dto.DateSearchDTO;
import ru.darin.non_working_days.util.DateResponse;
import ru.darin.non_working_days.util.DaysResponse;
import ru.darin.non_working_days.util.YearResponse;
import ru.darin.non_working_days.util.exception.NonWorkingDaysErrorResponse;

@Tag(name = "API сервиса определения количества нерабочих дней")
public interface NonWorkingDaysResource {
    @Operation(
            summary = "Получение данных за год.",
            description = "Данный метод предназначен для получения данных по всем выходным дням за год." +
                    "\nНеобходимо ввести интересующий нас год."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = YearResponse.class))
                            )
                    }
            )
    })
    ResponseEntity getCommonInfoAboutYear(@RequestParam(value = "year") Integer year);

    @Operation(
            summary = "Расчет даты.",
            description = "Данный метод предназначен для вычисления даты, которая наступит по истечении такого числа рабочих дней, которое было передано в качестве параметра." +
                    "\nНеобходимо ввести количество дней."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DateResponse.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NonWorkingDaysErrorResponse.class))
            )
    })
    ResponseEntity getDateAfterCountOfWorkingDays(@RequestBody @Valid CountSearchDTO countSearchDTO, BindingResult bindingResult);

    @Operation(
            summary = "Вычисление количества нерабочих дней.",
            description = "Данный метод предназначен для вычисления количества выходных дней за указанный период." +
                    "\nНеобходимо ввести диапазон дат."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Метод успешно выполнен",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = DaysResponse.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = NonWorkingDaysErrorResponse.class))
            )
    })
    ResponseEntity getCountOfNonWorkingDaysBetweenDays(@RequestBody @Valid DateSearchDTO dateSearchDTO, BindingResult bindingResult);
}
