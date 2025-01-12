package com.kjung.boilerplate.modulecommon.core.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class ErrorVo {
    @Schema(description = "Error code", example = "ERR-001")
    private final String errorCode;
    @Schema(description = "Request path", example = "/api/v1/sample")
    private final String path;
    @Schema(description = "Error trace", example = "java.lang.NullPointerException")
    private final String trace;
    @Schema(description = "Error message", example = "Invalid request")
    private final String message;

    public static ErrorVo of(String errorCode, String message, String path, String trace) {
        return ErrorVo.builder()
                .errorCode(errorCode)
                .message(message)
                .path(path)
                .trace(trace)
                .build();
    }
}
