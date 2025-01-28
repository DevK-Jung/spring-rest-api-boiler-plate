package com.kjung.boilerplate.modulecommon.core.base;

import com.kjung.boilerplate.modulecommon.core.response.ResponseWrapper;
import org.springframework.http.HttpStatus;

public abstract class AbstractController {

    protected final <T> ResponseWrapper<T> makeResponse(T data) {
        return ResponseWrapper.withStatus(HttpStatus.OK, data);
    }

    protected final <T> ResponseWrapper<T> makeResponse(HttpStatus status, T data) {
        return ResponseWrapper.withStatus(status, data);
    }

    protected final <T> ResponseWrapper<T> makeCreatedResponse(T data) {
        return ResponseWrapper.withStatus(HttpStatus.CREATED, data);
    }
}
