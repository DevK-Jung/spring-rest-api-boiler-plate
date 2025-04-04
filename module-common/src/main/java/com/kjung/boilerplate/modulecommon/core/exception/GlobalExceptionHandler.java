package com.kjung.boilerplate.modulecommon.core.exception;

import com.kjung.boilerplate.modulecommon.core.response.ResponseWrapper;
import com.kjung.boilerplate.modulecommon.core.utils.ResponseUtils;
import com.kjung.boilerplate.modulecommon.core.vo.ErrorVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @Value("${spring.error.response.include-stack-trace:false}")
    private boolean includeStackTrace;

    private final MessageSource messageSource;

    private String getMessageFromProperties(String code) {
        String defaultErrorMessage = "시스템 처리 중 에러가 발생했습니다.2";
        String result = messageSource.getMessage(code, null, defaultErrorMessage, Locale.getDefault());

        if (StringUtils.isBlank(result)) log.warn(">> message code 없음: {}", code);

        return result;
    }

    @ExceptionHandler(Exception.class)
    public ResponseWrapper<ErrorVo> exception(Exception ex) {
        String errorCode = "ERR_01";
        String message = getMessageFromProperties(errorCode);

        return ResponseUtils.makeErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                errorCode,
                message,
                ex,
                includeStackTrace
        );
    }

    @ExceptionHandler(BaseException.class)
    public ResponseWrapper<ErrorVo> handleBaseException(BaseException ex) {
        return ResponseUtils.makeErrorResponse(
                ex.getStatus(),
                ex.getErrorCode(),
                ex.getMessage(),
                ex,
                includeStackTrace
        );
    }

}