package com.kjung.boilerplate.modulecommon.core.interceptor;

import com.kjung.boilerplate.modulecommon.core.constants.RequestAttributeKey;
import com.kjung.boilerplate.modulecommon.core.vo.ReqContextVo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RequestContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request,
                             @NonNull HttpServletResponse response,
                             @NonNull Object handler) {

        ReqContextVo contextVo = ReqContextVo.from(request);

        request.setAttribute(RequestAttributeKey.REQUEST_CONTEXT.name(), contextVo);

        log.debug(">>>>> Request Context: {}", contextVo);

        return true;
    }
}
