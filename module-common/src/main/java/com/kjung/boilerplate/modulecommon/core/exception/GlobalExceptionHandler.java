package com.kjung.boilerplate.modulecommon.core.exception;

import com.kjung.boilerplate.modulecommon.core.response.ResponseWrapper;
import com.kjung.boilerplate.modulecommon.core.utils.ResponseUtils;
import com.kjung.boilerplate.modulecommon.core.vo.ErrorVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @Value("${spring.error.response.include-stack-trace:false}")
    private boolean includeStackTrace;

    @ExceptionHandler(Exception.class)
    public ResponseWrapper<ErrorVo> exception(Exception e) {
        String errorCode = "ERR_01";
        String message = "error message";

        return ResponseUtils.makeErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                errorCode,
                message,
                e,
                includeStackTrace
        );
    }
}