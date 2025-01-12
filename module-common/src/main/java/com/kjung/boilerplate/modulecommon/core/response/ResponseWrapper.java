package com.kjung.boilerplate.modulecommon.core.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ResponseWrapper<T>(
        @Schema(description = "HTTP Status Code", example = "OK")
        String statusCode,

        @Schema(description = "Numeric HTTP Status", example = "200")
        int status,

        @Schema(description = "Response data")
        T data,

        @Schema(description = "Timestamp of response", example = "2025-01-01 12:34:56:789")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss:SSS")
        LocalDateTime timestamp,

        @Schema(description = "Unique identifier for the request", example = "550e8400-e29b-41d4-a716-446655440000")
        String guid) {

    public static <T> ResponseWrapper<T> withStatus(@NonNull HttpStatus status,
                                                    T data) {
        return new ResponseWrapper<>(
                status.getReasonPhrase(),
                status.value(),
                data,
                LocalDateTime.now(),
                UUID.randomUUID().toString());
    }
}
