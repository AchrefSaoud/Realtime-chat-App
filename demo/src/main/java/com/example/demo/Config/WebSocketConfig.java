package com.example.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker/*This annotation enables WebSocket message handling, allowing your application to both send and receive messages using WebSocket. */
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{/*This interface provides methods to configure WebSocket message handling. */
    
    /*This method is used to register the STOMP (Simple Text Oriented Messaging Protocol) 
    endpoints with the specified registry. 
    STOMP is a messaging protocol that defines the format and rules for data exchange. */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
	}

    /*This method configures the message broker. 
    A message broker is responsible for routing messages between clients. Here, 
    you can define where the server should send messages to be distributed among connected clients.*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
	}


}
