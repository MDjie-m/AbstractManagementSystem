package com.renxin.webSocket;

import com.renxin.framework.web.service.ConsultantTokenService;
import com.renxin.framework.web.service.PocketTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Map;


/**
 * @author 黄远超
 */
@Component
@Slf4j
public class MyWsInterceptor extends HttpSessionHandshakeInterceptor {
    @Resource
    private PocketTokenService pocketTokenService;

    @Resource
    ConsultantTokenService consultantTokenService;
    
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
       // Long consultId = consultantTokenService.getConsultId(request);
        
        // 使用 UriComponentsBuilder 解析查询参数
        URI uri = request.getURI();
        Map<String, String> queryParams = UriComponentsBuilder.fromUri(uri).build().getQueryParams().toSingleValueMap();

        // 获取并设置 userType 和 userId
        String userType = queryParams.get("userType");
        String token = queryParams.get("token");
        
        // 将它们放入 attributes 以便在 WebSocketSession 中使用
        attributes.put("userType", userType);
        attributes.put("token", token);
        
        return super.beforeHandshake(request, response, wsHandler, attributes);
        
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        super.afterHandshake(request, response, wsHandler, ex);
    }
}