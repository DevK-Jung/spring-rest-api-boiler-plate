package com.kjung.boilerplate.modulecommon.core.vo;

import com.kjung.boilerplate.modulecommon.core.utils.IpUtils;
import com.kjung.boilerplate.modulecommon.core.utils.UuidUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpMethod;

import java.time.LocalDateTime;

@Getter
@ToString
public class ReqContextVo {
    private final String requestId;
    private final LocalDateTime requestTimestamp;
    private final String requestUri;
    private final String clientIp;
    private final HttpMethod httpMethod;

    public static ReqContextVo from(HttpServletRequest request) {
        return new ReqContextVo(request.getRequestURI(),
                IpUtils.getClientIp(request),
                HttpMethod.valueOf(request.getMethod()));
    }

    private ReqContextVo(String requestUri,
                         String clientIp,
                         HttpMethod httpMethod) {

        this.requestId = UuidUtils.generate();
        this.requestTimestamp = LocalDateTime.now();
        this.requestUri = requestUri;
        this.clientIp = clientIp;
        this.httpMethod = httpMethod;
    }

}
