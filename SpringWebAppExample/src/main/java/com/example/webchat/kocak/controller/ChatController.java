package com.example.webchat.kocak.controller;

import com.example.webchat.kocak.model.CheckMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public CheckMessage sendMessage(@Payload final CheckMessage checkMessage){
        return checkMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public")
    public CheckMessage newUser(@Payload final CheckMessage checkMessage, SimpMessageHeaderAccessor headerAccessor){
        headerAccessor.getSessionAttributes().put("username", checkMessage.getSender());
        return checkMessage;
    }
}
