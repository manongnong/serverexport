package com.dswdemo.serverexport.websocket.controller;


import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import com.dswdemo.serverexport.websocket.handler.SpringWebSocketHandler;
import com.dswdemo.serverexport.websocket.model.AppRepository;
import com.dswdemo.serverexport.websocket.model.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebSocketController {

    private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private AppRepository appRepository;

    @Autowired
    public SpringWebSocketHandler springWebSocketHandler;


    @GetMapping("/echo")
    @ResponseBody
    public String echo() {
        return "echo";
    }

    /**
     * 发送初始化脚本命令
     * @param request 请求
     * @return 结果
     */
    @RequestMapping("/websocket/sendInitCommand")
    @ResponseBody
    public String sendInitCommand(HttpServletRequest request) {
        Command cmd1 = new Command();
        cmd1.setType("1");
        cmd1.setContent(ResourceUtil.readUtf8Str("js/jquery-1.12.4.js"));
        logger.info("发送init消息");
        springWebSocketHandler.sendMessageToUsers(new TextMessage(JSONUtil.toJsonStr(cmd1)));
        return "success";
    }

    /**
     * 发送导出命令
     * @param request
     * @return
     */
    @RequestMapping("/websocket/sendExportCommand")
    @ResponseBody
    public String sendExportCommand(HttpServletRequest request) {
        Command cmd = new Command();
        cmd.setType("2");
        cmd.setContent(ResourceUtil.readUtf8Str("app1/queryApp.js"));
        logger.info("发送消息：{}",JSONUtil.toJsonStr(cmd));
        springWebSocketHandler.sendMessageToUsers(new TextMessage(JSONUtil.toJsonStr(cmd)));
        return "success";
    }



}
