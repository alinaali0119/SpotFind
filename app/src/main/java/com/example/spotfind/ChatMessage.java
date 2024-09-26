package com.example.spotfind;

public class ChatMessage {
    public static final String SENDER_USER = "user";
    public static final String SENDER_BOT = "bot";

    public static final int VIEW_TYPE_USER = 1;
    public static final int VIEW_TYPE_BOT = 2;

    private String sender;
    private String message;

    public ChatMessage(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    // Getter and Setter methods
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


