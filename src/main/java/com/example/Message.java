package com.example;

import java.util.Random;

public class Message {
    private String messageID;
    private int numMessagesSent;
    private String recipient;
    private String messageContent;
    private String messageHash;
    private String messageStatus;

    // Regular constructor from Part 2
    public Message(int numMessagesSent, String recipient, String messageContent) {
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageID = generateRandomID();
        this.messageHash = createMessageHash();
    }

    // Overloaded constructor for Part 3 Test Data injection
    public Message(int numMessagesSent, String recipient, String messageContent, String customID) {
        this.numMessagesSent = numMessagesSent;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageID = customID;
        this.messageHash = createMessageHash();
    }

    private String generateRandomID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    public String checkRecipientCell() {
        // Updated regex to accept either +27 format or 10-digit raw numbers from test data sheets
        if (recipient != null && (recipient.matches("^\\+27\\d{9}$") || recipient.matches("^\\d{10}$"))) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not "
             + "contain an international code. Please correct the number and try again.";
    }

    public String createMessageHash() {
        String idPart = (messageID != null && messageID.length() >= 2) ? 
                        messageID.substring(0, 2) : "00";
        
        String cleanMsg = (messageContent != null) ? messageContent.trim() : "";
        String firstWord = "";
        String lastWord = "";

        if (!cleanMsg.isEmpty()) {
            String[] words = cleanMsg.split("\\s+");
            if (words.length > 0) {
                firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
                lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
            }
        } else {
            firstWord = "EMPTY";
            lastWord = "EMPTY";
        }

        String combinedWords = (firstWord + lastWord).toUpperCase();
        return idPart + ":" + numMessagesSent + ":" + combinedWords;
    }

    public String validateMessageLength() {
        if (this.messageContent == null || this.messageContent.length() > 250) {
            int exceeded = (this.messageContent != null) ? this.messageContent.length() - 250 : 0;
            return "Message exceeds 250 characters by " + exceeded 
                 + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    public String SentMessage(int choice) {
        if (choice == 1) {
            this.messageStatus = "Sent";
            return "Message successfully sent.";
        } else if (choice == 2) {
            this.messageStatus = "Disregarded";
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            this.messageStatus = "Stored";
            return "Message successfully stored.";
        }
        this.messageStatus = "Unknown";
        return "Invalid choice.";
    }

    public String printMessages() {
        return "Message ID: " + messageID + "\n"
             + "Message Hash: " + messageHash + "\n"
             + "Recipient: " + recipient + "\n"
             + "Message: " + messageContent;
    }

    public int returnTotalMessagess() {
        return this.numMessagesSent;
    }

    public void storeMessage() {
        String jsonMock = "{\n"
                        + "  \"messageID\": \"" + messageID + "\",\n"
                        + "  \"recipient\": \"" + recipient + "\",\n"
                        + "  \"status\": \"" + messageStatus + "\"\n"
                        + "}";
        System.out.println("\n[JSON Research Export saved to memory]:\n" + jsonMock);
    }

    // Getters and Setters needed for Part 3 operations
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
    public String getMessageStatus() { return messageStatus; }
    public void setMessageStatus(String status) { this.messageStatus = status; }
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
}
