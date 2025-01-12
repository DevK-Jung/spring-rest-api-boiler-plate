package com.kjung.boilerplate.modulecommon.core.base;

import com.kjung.boilerplate.modulecommon.core.response.ResponseWrapper;
import org.springframework.http.HttpStatus;

public abstract class AbstractController {

    protected final <T> ResponseWrapper<T> makeOkResponse(T data) {
        return ResponseWrapper.withStatus(HttpStatus.OK, data);
    }

    protected final <T> ResponseWrapper<T> makeCreatedResponse(T data) {
        return ResponseWrapper.withStatus(HttpStatus.CREATED, data);
    }
}
