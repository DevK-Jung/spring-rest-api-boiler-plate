package com.kjung.boilerplate.modulecommon.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@UtilityClass
public class ServletUtils {
    public HttpServletRequest getServletRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes == null) return null;

        return attributes.getRequest();
    }

    public String getRequestURI() {
        HttpServletRequest request = getServletRequest();

        return request == null ? null : request.getRequestURI();
    }
}
