package com.kjung.boilerplate.moduleapi.core.exception;

import com.kjung.boilerplate.modulecommon.core.exception.BaseException;
import org.springframework.http.HttpStatus;

public class CustomException extends BaseException {
    public CustomException() {
        super(HttpStatus.BAD_REQUEST, "ERROR_CODE_01", "Custom Error Message");
    }
}
