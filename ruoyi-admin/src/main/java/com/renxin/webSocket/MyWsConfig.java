package com.renxin.webSocket;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.Resource;


/**
 * @author 黄远超
 */
@Configuration
@EnableWebSocket
public class MyWsConfig implements WebSocketConfigurer {
    @Resource
    MyWsHandler myWsHandler;
    @Resource
    MyWsInterceptor myWsInterceptor;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWsHandler,"/message")
                .addInterceptors(myWsInterceptor)
                .setAllowedOrigins("*");
    }
    //访问的位置，设置拦截器，设置拦截范围
}