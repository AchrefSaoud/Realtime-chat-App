package com.example.demo.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/*
 
        sendMessage: This method is mapped to the "/chat.sendMessage" destination. 
        When a client sends a message to this destination, this method is invoked. 
        It takes a ChatMessage object as payload, representing the message sent by the client. 
        Inside the method, it simply returns the same ChatMessage object. Additionally,
        it uses @SendTo("/topic/public") annotation, which means that after processing the message,
        it will be sent to all clients subscribed to the "/topic/public" destination.
        
        addUser: This method is mapped to the "/chat.addUser" destination. 
        When a client sends a message to this destination, 
        this method is invoked. 
        It takes two parameters: a ChatMessage object representing the message sent by the client, 
        and a SimpMessageHeaderAccessor object, which provides access to message headers and attributes.
        Inside this method, it sets the username of the sender in the session attributes using simpMessageHeaderAccessor.getSessionAttributes().put("username", chatMessage.getSender()). 
        This allows the server to keep track of the username associated with the session.
        Like sendMessage, it also returns the same ChatMessage object. Additionally,
        it uses @SendTo("/topic/public") annotation, 
        so the message will be sent to all clients subscribed to the "/topic/public" destination.
 */
@Controller
public class chatController {
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        return chatMessage;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,SimpMessageHeaderAccessor simpMessageHeaderAccessor){
        
        simpMessageHeaderAccessor.getSessionAttributes().put("username",chatMessage.getSender());
        return chatMessage;
    }
}
