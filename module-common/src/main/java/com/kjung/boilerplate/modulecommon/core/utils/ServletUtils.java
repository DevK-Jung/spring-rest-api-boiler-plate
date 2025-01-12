package com.kjung.boilerplate.modulecommon.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@UtilityClass
public class ServletUtils {
    public HttpServletRequest getServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        return attributes.getRequest();
    }

    public String getRequestURI() {
        return getServletRequest().getRequestURI();
    }
}
