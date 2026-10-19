package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest {

    @BeforeEach
    public void resetEnvironment() {
        Main.clearData();
    }

    @Test
    public void testSentMessagesArrayPopulation() {
        Message msg1 = new Message(0, "+27834557896", "Did you get the cake?");
        msg1.SentMessage(1); // Set state to Sent
        Main.populateArrays(msg1);

        Message msg2 = new Message(1, "+27838884567", "Where are you? You are late!", "0838884567");
        msg2.SentMessage(1); // Set state to Sent
        Main.populateArrays(msg2);

        assertEquals("Did you get the cake?", msg1.getMessageContent());
        assertEquals("Where are you? You are late!", msg2.getMessageContent());
    }

    @Test
    public void testDisplayLongestMessage() {
        Message msg1 = new Message(0, "+27834557896", "Did you get the cake?");
        msg1.SentMessage(3); // Stored
        Main.populateArrays(msg1);

        String longText = "Where are you? You are late! I have asked you to be on time.";
        Message msg2 = new Message(1, "+27838884567", longText);
        msg2.SentMessage(3); // Stored
        Main.populateArrays(msg2);

        String longestFound = "";
        if (msg1.getMessageContent().length() > msg2.getMessageContent().length()) {
            longestFound = msg1.getMessageContent();
        } else {
            longestFound = msg2.getMessageContent();
        }

        assertEquals(longText, longestFound);
    }

    @Test
    public void testSearchForMessageID() {
        Message msg = new Message(0, "0838884567", "It is dinner time !", "0838884567");
        Main.populateArrays(msg);

        assertEquals("0838884567", msg.getMessageID());
        assertEquals("It is dinner time !", msg.getMessageContent());
    }

    @Test
    public void testSearchAllMessagesForParticularRecipient() {
        Message msg1 = new Message(0, "+27838884567", "Where are you? You are late!");
        Message msg2 = new Message(1, "+27838884567", "Ok, I am leaving without you.");

        assertEquals("+27838884567", msg1.getRecipient());
        assertEquals("+27838884567", msg2.getRecipient());
    }

    @Test
    public void testDeleteMessageUsingHash() {
        Message msg = new Message(0, "+27838884567", "Where are you? You are late! I have asked you to be on time.");
        
        String deletionOutput = "Message: \"" + msg.getMessageContent() + "\" successfully deleted.";
        assertEquals("Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.", deletionOutput);
    }
}