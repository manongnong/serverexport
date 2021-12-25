package com.dswdemo.serverexport.websocket;

import com.dswdemo.serverexport.websocket.config.WebSocketConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import({WebSocketConfig.class})
public class SpringBootServerExportWebsocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootServerExportWebsocketApplication.class, args);
    }

}

