package fr.miage.ter.comparcom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Controller
public class HelloWorldWebSocket implements WebSocketMessageBrokerConfigurer {

    Logger logger = LoggerFactory.getLogger(HelloWorldWebSocket.class);

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
        registry.addEndpoint("/websocket").setAllowedOriginPatterns("*").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        config.enableSimpleBroker("/");
        config.setApplicationDestinationPrefixes("/");
    }

    @MessageMapping("/helloSpringWS")
    @SendTo("/helloJSWS")
    public String helloWorldWebSocket(@Payload String message){
        logger.info("Message received from websocket/stomp");
        logger.info(message);
        return "SpringWS say hello back to Javascript";
    }

}
