package com.kjung.boilerplate.modulecommon.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class IpUtils {
    public String getClientIp(HttpServletRequest request) {
        // Check headers set by proxies or load balancers
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // X-Forwarded-For can contain a list of IPs. The first one is the client's IP.
            return ip.split(",")[0].trim();
        }

        ip = request.getHeader("X-Real-IP");
        if (StringUtils.isNotEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }

        // Fall back to the remote address
        return request.getRemoteAddr();
    }
}
