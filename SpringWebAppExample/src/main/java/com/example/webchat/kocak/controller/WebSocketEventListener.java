package com.example.webchat.kocak.controller;


import com.example.webchat.kocak.model.CheckMessage;
import com.example.webchat.kocak.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations sendingOperations;


    @EventListener
    public  void handleWebSocketConnectListener(final SessionConnectedEvent event){
        LOGGER.info("Bing bong bing. We habe a new cheeky little connection!");
    }


    @EventListener
    public void hangdeWebSocketDisconnectionListener(final SessionConnectedEvent event){
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String)headerAccessor.getSessionAttributes().get("username");
        final CheckMessage checkMessage = CheckMessage.builder().type(MessageType.DISCONNECT).sender(username).build();
        sendingOperations.convertAndSend("/topic/public",checkMessage);
    }

}
