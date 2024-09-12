package com.renxin.webSocket;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;


/**
 * @author 黄远超
 */
/**
 * SessionBean类用于封装与WebSocket会话相关的数据。
 *  webSocketSession 代表一个WebSocket会话的对象，用于与客户端进行双向通信。
 *  clientId 客户端的唯一标识符，用于区分不同的客户端连接。
 */
@Data
@AllArgsConstructor
public class SessionBean {
    private WebSocketSession webSocketSession; // WebSocket会话对象
    private Integer clientId; // 客户端ID
}