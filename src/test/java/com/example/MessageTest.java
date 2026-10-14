package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class MessageTest {

    @Test
    public void testMessageLength_Success() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.validateMessageLength());
    }

    @Test
    public void testMessageLength_Failure() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 260; i++) sb.append("A");
        Message msg = new Message(0, "+27718693002", sb.toString());
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", msg.validateMessageLength());
    }

    @Test
    public void testRecipientCell_Success() {
        Message msg = new Message(0, "+27718693002", "Valid text input.");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientCell_Failure() {
        Message msg = new Message(0, "08575975889", "Valid text input.");
        assertTrue(msg.checkRecipientCell().contains("incorrectly formatted"));
    }

    @Test
    public void testMessageHashGeneration() {
        Message msg = new Message(0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String randomIdPart = msg.getMessageID().substring(0, 2);
        assertEquals(randomIdPart + ":0:HITONIGHT", msg.getMessageHash());
    }
}
