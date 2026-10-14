package com.example;

public class Message {
    // These are the base tracking fields
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String messageContent;
    private String messageHash;
    private String messageStatus;

    public Message(int numMessagesSent, String recipient, String messageContent) {
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        
    }
}