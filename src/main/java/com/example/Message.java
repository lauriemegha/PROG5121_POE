package com.example;

import java.util.Random;

public class Message {
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
        this.messageID = generateRandomID(); // Added in step 2
    }

    // Added in step 2: Generates the random tracking ID
    private String generateRandomID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Added in step 2: Validates the ID length
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // Added in step 2: Validates the phone format
    public String checkRecipientCell() {
        if (recipient != null && recipient.matches("^\\+27\\d{9}$") && recipient.length() == 12) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not "
             + "contain an international code. Please correct the number and try again.";
    }
}
