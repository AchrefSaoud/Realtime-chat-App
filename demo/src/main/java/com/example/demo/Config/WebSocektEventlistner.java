package com.example.demo.Config;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.example.demo.chat.ChatMessage;
import com.example.demo.chat.MessageType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class WebSocektEventlistner {
    private final SimpMessageSendingOperations messagetemplate;

    @EventListener
    public void handleWebSocketDisconnectListneer(SessionDisconnectEvent event){
        StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
        String username=(String) headerAccessor.getSessionAttributes().get("username");
        if(username!=null){
            log.info("user disconnected:{}",username);
            var chatMessage=ChatMessage.builder()
                            .messageTpe(MessageType.LEAVE)
                            .Sender(username)
                            .build();
            messagetemplate.convertAndSend("/topic/public",chatMessage);            
        }
    }
}
