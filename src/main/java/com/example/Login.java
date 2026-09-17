package com.example;

public class Login {
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    // Constructor to set up the registration fields
    public Login(String username, String password, String phoneNumber, 
    String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Checks that the username contains an underscore and is 5 characters or less
    public boolean checkUserName() {
        return username != null && 
               username.contains("_") && 
               username.length() <= 5;
    }

    // Validates password complexity against required rules
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) return false;
        boolean upper = false, digit = false, special = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) upper = true;
            else if (Character.isDigit(c)) digit = true;
            else if (!Character.isLetterOrDigit(c)) special = true;
        }
        return upper && digit && special;
    }

    // Validates international phone formatting (+27 prefix)
    public boolean checkPhoneNumber() {
        return phoneNumber != null && 
               phoneNumber.matches("^\\+27\\d{9,10}$");
    }

    // Generates registration messages per assignment string tables
    public String registerUser() {
        String uMsg = checkUserName() ? 
            "Username successfully captured." : 
            "Username is not correctly formatted, please ensure that "
            + "your username contains an underscore and is no more "
            + "than 5 characters in length .";
        
        String pMsg = checkPasswordComplexity() ? 
            "Password successfully captured" : 
            "Password is not correctly formatted, please ensure that "
            + "the password contains at least 8 characters, "
            + "a capital letter, a number and a special character.";
            
        String phMsg = checkPhoneNumber() ? 
            "Cell phone number successfully added." : 
            "Cell phone number incorrectly formatted or does not contain "
            + "an international code.";

        return uMsg + "\n" + pMsg + "\n" + phMsg;
    }

    // Authenticates user match credentials
    public boolean loginUser(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && 
               this.password.equals(inputPassword);
    }

    // Returns structural user greeting message arrays
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + " ," + lastName 
                 + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again";
        }
    }
}
