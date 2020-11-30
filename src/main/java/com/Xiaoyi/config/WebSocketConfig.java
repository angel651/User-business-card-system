package com.Xiaoyi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @ Author     ：Xiaoyi
 * @ Date       ：Created in 20:25 2020/11/30
 * @ Description：开启WebSocket
 * @ Modified By：
 * @Version: 1.0
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
