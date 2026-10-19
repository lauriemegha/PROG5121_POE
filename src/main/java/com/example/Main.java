package com.example;

import java.util.Scanner;

public class Main {
    // Parallel array storage limits set to capacity of 100 entries
    private static String[] sentMessagesArray = new String[100];
    private static String[] disregardedMessagesArray = new String[100];
    private static String[] storedMessagesArray = new String[100];
    private static String[] messageHashArray = new String[100];
    private static String[] messageIdArray = new String[100];
    private static Message[] rawMessagesLog = new Message[100];
    private static int globalCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=============== REGISTRATION ===============");
        System.out.println("Enter First Name: "); String fName = scanner.nextLine();
        System.out.println("Enter Last Name: ");  String lName = scanner.nextLine();
        System.out.println("Enter Username: ");   String uName = scanner.nextLine();
        System.out.println("Enter Password: ");   String pWord = scanner.nextLine();
        System.out.println("Enter Phone Number: "); String phone = scanner.nextLine();

        System.out.println("=============== REGISTRATION RESULTS ===============");
        Login user = new Login(uName, pWord, phone, fName, lName);
        System.out.println("\n" + user.registerUser());
        
        if (user.checkUserName() && user.checkPasswordComplexity() && user.checkPhoneNumber()) {
            
            System.out.println("\n=============== LOGIN USER ===============");
            System.out.println("Username: "); String inUser = scanner.nextLine();
            System.out.println("Password: "); String inPass = scanner.nextLine();

            boolean isAuthenticated = user.loginUser(inUser, inPass);
            System.out.println("\n" + user.returnLoginStatus(isAuthenticated));

            if (isAuthenticated) {
                System.out.println("\nWelcome to QuickChat.");
                
                System.out.println("How many messages do you wish to enter? ");
                int maxMessages = scanner.nextInt();
                scanner.nextLine(); // Clear scanner buffer

                Message[] chatLog = new Message[maxMessages];
                int messageCounter = 0;

                boolean running = true;
                while (running) {
                    System.out.println("\n=============== QUICKCHAT MENU ===============");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Stored Messages Menu (Part 3 Options)");
                    System.out.println("4) Quit");
                    System.out.print("Choose an option: ");
                    
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Clear scanner buffer

                    switch (option) {
                        case 1:
                            // ... (keep case 1 exactly as it is)
                            break;

                        case 2:
                            System.out.println("\nComing Soon.");
                            break;

                        case 3:
                            // Calls your sub-menu dashboard loop right here
                            handleStoredMessagesMenu(scanner);
                            break;

                        case 4:
                            running = false;
                            System.out.println("\n=========================================");
                            int finalTotal = (messageCounter > 0) ? 
                                             chatLog[messageCounter - 1].returnTotalMessagess() + 1 : 0;
                            System.out.println("Total number of messages processed: " + finalTotal);
                            System.out.println("Exiting QuickChat application. Goodbye.");
                            System.out.println("=========================================");
                            break;

                        default:
                            System.out.println("Invalid selection. Try again.");
                    }
                }

            }
        } else {
            System.out.println("\nRegistration metrics failed. App terminated.");
        }
        scanner.close();
    }

    private static void handleStoredMessagesMenu(Scanner scanner) {
        System.out.println("\n----- STORED MESSAGES REPORT DASHBOARD -----");
        System.out.println("a) Display recipient of all stored messages");
        System.out.println("b) Display the longest stored message");
        System.out.println("c) Search for a message ID (Coming Soon)");
        System.out.println("d) Search all messages for a particular recipient (Coming Soon)");
        System.out.println("e) Delete a message using message hash (Coming Soon)");
        System.out.println("f) Display report of all messages (Coming Soon)");
        System.out.print("Select feature letter: ");
        String choice = scanner.nextLine().toLowerCase();

        switch (choice) {
            case "a":
                System.out.println("\n--- STORED MESSAGES CONTACTS ---");
                for (int i = 0; i < globalCount; i++) {
                    if (rawMessagesLog[i] != null && "Stored".equals(rawMessagesLog[i].getMessageStatus())) {
                        System.out.println("Recipient: " + rawMessagesLog[i].getRecipient());
                    }
                }
                break;

            case "b":
                String longest = "";
                for (int i = 0; i < globalCount; i++) {
                    if (rawMessagesLog[i] != null && "Stored".equals(rawMessagesLog[i].getMessageStatus())) {
                        if (rawMessagesLog[i].getMessageContent().length() > longest.length()) {
                            longest = rawMessagesLog[i].getMessageContent();
                        }
                    }
                }
                System.out.println("\nLongest Stored Message: " + longest);
                break;

            case "c":
                System.out.print("\nEnter Message ID to look up: ");
                String searchID = scanner.nextLine();
                boolean idFound = false;
                for (int i = 0; i < globalCount; i++) {
                    if (rawMessagesLog[i] != null && rawMessagesLog[i].getMessageID().equals(searchID)) {
                        System.out.println("Message: " + rawMessagesLog[i].getMessageContent());
                        idFound = true;
                        break;
                    }
                }
                if (!idFound) System.out.println("Message ID not found.");
                break;

            case "d":
                System.out.print("\nEnter Recipient Number to filter: ");
                String searchTarget = scanner.nextLine();
                for (int i = 0; i < globalCount; i++) {
                    if (rawMessagesLog[i] != null && rawMessagesLog[i].getRecipient().equals(searchTarget)) {
                        System.out.println("- " + rawMessagesLog[i].getMessageContent());
                    }
                }
                break;

            default:
                System.out.println("Invalid reporting selection choice.");
        }
    }

    public static void populateArrays(Message msg) {
        rawMessagesLog[globalCount] = msg;
        messageHashArray[globalCount] = msg.getMessageHash();
        messageIdArray[globalCount] = msg.getMessageID();

        if ("Sent".equals(msg.getMessageStatus())) {
            sentMessagesArray[globalCount] = msg.getMessageContent();
        } else if ("Disregarded".equals(msg.getMessageStatus())) {
            disregardedMessagesArray[globalCount] = msg.getMessageContent();
        } else if ("Stored".equals(msg.getMessageStatus())) {
            storedMessagesArray[globalCount] = msg.getMessageContent();
        }
        globalCount++;
    }

    public static void clearData() { globalCount = 0; }
    public static int getGlobalCount() { return globalCount; }
}