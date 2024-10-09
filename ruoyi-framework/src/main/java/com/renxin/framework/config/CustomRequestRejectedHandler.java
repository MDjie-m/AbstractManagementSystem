package com.renxin.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.security.web.firewall.RequestRejectedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomRequestRejectedHandler implements RequestRejectedHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestRejectedHandler.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, RequestRejectedException exception) throws IOException {
        // 打印完整的请求URL
        String fullURL = request.getRequestURL().toString();
        if (request.getQueryString() != null) {
            fullURL += "?" + request.getQueryString();
        }
        logger.error("请求异常RequestRejectedException: URL = {}, Error = {}", fullURL, exception.getMessage());

        // 这里可以自定义返回错误信息给请求者
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Bad request");
    }
}
