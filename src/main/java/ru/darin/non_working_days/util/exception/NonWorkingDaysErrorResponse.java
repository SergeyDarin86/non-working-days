package ru.darin.non_working_days.util.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Класс, возвращающий текст ошибки пользователю в случае некорректно введенных данных.")
public class NonWorkingDaysErrorResponse {
    @Schema(
            description = "Текст сообщения об ошибке",
            example = "Поле не может быть пустым"
    )
    private String message;

    @Schema(description = "Временная метка (timestamp)", example = "1729178966070")
    private long timestamp;

    public NonWorkingDaysErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
