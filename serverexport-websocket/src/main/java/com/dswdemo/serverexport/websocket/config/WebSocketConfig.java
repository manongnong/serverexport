package com.dswdemo.serverexport.websocket.config;

import com.dswdemo.serverexport.websocket.handler.SpringWebSocketHandler;
import com.dswdemo.serverexport.websocket.handler.SpringWebSocketHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;


@Configuration
@EnableWebSocket
public class WebSocketConfig  implements WebSocketConfigurer {

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(512000);
        container.setMaxBinaryMessageBufferSize(512000);
        container.setMaxSessionIdleTimeout(15 * 60000L);
        return container;
    }


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(webSocketHandler(),"/websocket/socketServer.do").setAllowedOrigins("*")
            .addInterceptors(new SpringWebSocketHandlerInterceptor());

        registry.addHandler(webSocketHandler(), "/sockjs/socketServer.do").setAllowedOrigins("*")
            .addInterceptors(new SpringWebSocketHandlerInterceptor()).withSockJS();
    }

    @Bean
    public SpringWebSocketHandler webSocketHandler(){
        return new SpringWebSocketHandler();
    }


}
