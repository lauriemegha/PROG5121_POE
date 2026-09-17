package com.example;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class LoginTest {

    @Test
    public void testCheckUserName_Success() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", 
        "+27838968976", "John", "Doe");
        assertTrue(login.checkUserName());
    }

    @Test
    public void testCheckUserName_Failure() {
        Login login = new Login("kyle!!!!!!", "Ch&&sec@ke99!", 
        "+27838968976", "John", "Doe");
        assertFalse(login.checkUserName());
    }

    @Test
    public void testPasswordComplexity_Success() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", 
        "+27838968976", "John", "Doe");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    public void testPasswordComplexity_Failure() {
        Login login = new Login("kyl_1", "password", 
        "+27838968976", "John", "Doe");
        assertFalse(login.checkPasswordComplexity());
    }
}
