package com.kjung.boilerplate.modulecommon.core.utils;


import com.kjung.boilerplate.modulecommon.core.response.ResponseWrapper;
import com.kjung.boilerplate.modulecommon.core.vo.ErrorVo;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

import java.io.PrintWriter;
import java.io.StringWriter;

@UtilityClass
public class ResponseUtils {
    public <T> ResponseWrapper<T> makeResponse(HttpStatus httpStatus,
                                               final T body) {
//        ReqContextVo contextVo = ServletReqUtils.getServletRequestContextVo();
//        respVo.setContextVo(contextVo);

//        String requestId = contextVo == null ? null : contextVo.getGuid();
//        if (requestId != null) respVo.setGuid(requestId);

        return ResponseWrapper.withStatus(httpStatus, body);
    }

    public ResponseWrapper<ErrorVo> makeErrorResponse(HttpStatus httpStatus,
                                                      String errorCode,
                                                      String message,
                                                      Throwable th,
                                                      boolean includeStackTrace) {

        String path = ServletUtils.getRequestURI();

        ErrorVo errorVo = makeErrorVo(errorCode, message, path, th, includeStackTrace);

        return makeResponse(httpStatus, errorVo);
    }

    public ErrorVo makeErrorVo(String errorCode,
                               String message,
                               String path,
                               Throwable th,
                               boolean includeStackTrace) {

        return makeErrorVo(errorCode, message, path, includeStackTrace ? convertStackTrace(th) : "");
    }

    private ErrorVo makeErrorVo(String errorCode, String message, String path, String trace) {
        return ErrorVo.of(errorCode, message, path, trace);
    }

    private String convertStackTrace(Throwable th) {
        if (th == null) return "";

        StringWriter writer = new StringWriter();
        th.printStackTrace(new PrintWriter(writer));

        return writer.toString();
    }

}
