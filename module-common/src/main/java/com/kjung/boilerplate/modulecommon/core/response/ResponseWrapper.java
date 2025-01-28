package com.kjung.boilerplate.modulecommon.core.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kjung.boilerplate.modulecommon.core.utils.ReqContextUtils;
import com.kjung.boilerplate.modulecommon.core.utils.UuidUtils;
import com.kjung.boilerplate.modulecommon.core.vo.ReqContextVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

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
        String requestId,
        @JsonIgnore
        @Schema(hidden = true)
        ReqContextVo reqContext) {

    public static <T> ResponseWrapper<T> withStatus(@NonNull HttpStatus status,
                                                    T data) {

        ReqContextVo reqContext = ReqContextUtils.getReqContext();

        String requestId = reqContext != null ? reqContext.getRequestId() : UuidUtils.generate();

        return new ResponseWrapper<>(
                status.getReasonPhrase(),
                status.value(),
                data,
                LocalDateTime.now(),
                requestId,
                reqContext);
    }
}
