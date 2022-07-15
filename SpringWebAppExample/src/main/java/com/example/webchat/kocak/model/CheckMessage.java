package com.example.webchat.kocak.model;


import lombok.Builder;
import lombok.Getter;

@Builder
public class CheckMessage {

    @Getter
    private MessageType type;

    @Getter
    private String content;

    @Getter
    private String sender;

    @Getter
    private String time;
}
