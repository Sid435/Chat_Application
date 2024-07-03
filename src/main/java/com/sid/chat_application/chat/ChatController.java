package com.sid.chat_application.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.sendMessage") //URL to invoke the method
    @SendTo("/topic/public") // URL to which the message is to hbe sent : this queue
    public ChatMessage sendMessage(
           @Payload ChatMessage chatMessage
    ){
        return chatMessage;
    }

    @MessageMapping("/chat.adduser") //URL to invoke the method
    @SendTo("/topic/public") // URL to which the message is to hbe sent : this queue
    public ChatMessage user(
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ){
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());// adds the username to websocket session
        return chatMessage;
    }
}
