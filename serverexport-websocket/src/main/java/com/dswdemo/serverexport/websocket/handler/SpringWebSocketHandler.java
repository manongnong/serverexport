package com.dswdemo.serverexport.websocket.handler;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.dswdemo.serverexport.websocket.model.AppInfo;
import com.dswdemo.serverexport.websocket.model.AppRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpringWebSocketHandler extends TextWebSocketHandler {

    private static Logger logger = LoggerFactory.getLogger(SpringWebSocketHandler.class);

    private static final Map<String, WebSocketSession> users;

    static {
        users =  new HashMap<>();
    }

    public SpringWebSocketHandler() {}

    @Autowired
    private AppRepository appRepository;


    /**
     * 建立连接监听
     * @param session
     * @throws Exception
     */
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("用户{},成功建立websocket连接!",session.getId());
        users.put(session.getId(),session);
        logger.info("当前线上用户数量{}!",users.size());
    }

    /**
     * 关闭连接监听
     * @param session
     * @param closeStatus
     * @throws Exception
     */
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("用户{},关闭建立websocket连接!",session.getId());
        users.remove(session.getId());
        logger.info("当前线上用户数量{}!",users.size());
    }

    /**
     * js调用websocket.send时候，会调用该方法
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        logger.info("服务器收到消息{}!",message);
        JSONArray jsonArray =  JSONUtil.parseArray(message.getPayload());
        List<AppInfo> appInfoList =  JSONUtil.toList(jsonArray, AppInfo.class);
        appRepository.saveAll(appInfoList);
        logger.info("保存数据完成{}!",appInfoList.size());
        message.getPayload();
    }

    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if(session.isOpen()){
            logger.error("用户{},发生异常，需要关闭!",session.getId(),exception);
            session.close();
        }
    }

    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (String userId : users.keySet()) {
            try {
                if (users.get(userId).isOpen()) {
                    users.get(userId).sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
