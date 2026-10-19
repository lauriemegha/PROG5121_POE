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
                    System.out.println("3) Quit");
                    System.out.print("Choose an option: ");
                    
                    int option = scanner.nextInt();
                    scanner.nextLine(); // Clear scanner buffer

                    switch (option) {
                        case 1:
                            if (messageCounter >= maxMessages) {
                                System.out.println("You have reached your set limit of " 
                                                 + maxMessages + " messages.");
                                break;
                            }

                            System.out.println("\nEnter Recipient Cell Number (+27XXXXXXXXX): ");
                            String targetCell = scanner.nextLine();

                            System.out.println("Enter Message (Max 250 characters): ");
                            String content = scanner.nextLine();

                            Message tempMsg = new Message(messageCounter, targetCell, content);

                            String cellCheck = tempMsg.checkRecipientCell();
                            String lengthCheck = tempMsg.validateMessageLength();

                            System.out.println("\n" + cellCheck);
                            System.out.println(lengthCheck);

                            if (cellCheck.contains("successfully") && lengthCheck.contains("ready")) {
                                System.out.println("\nAction required for completed message:");
                                System.out.println("1 - Send Message");
                                System.out.println("2 - Disregard Message");
                                System.out.println("3 - Store Message to send later");
                                System.out.print("Select action: ");
                                int actionChoice = scanner.nextInt();
                                scanner.nextLine();

                                System.out.println("\n" + tempMsg.SentMessage(actionChoice));
                                
                                if (actionChoice == 1 || actionChoice == 3) {
                                    chatLog[messageCounter] = tempMsg;
                                    messageCounter++;
                                    
                                    System.out.println("\n=============== FULL MESSAGE DETAILS ===============");
                                    System.out.println(tempMsg.printMessages());
                                    tempMsg.storeMessage(); 
                                }
                            } else {
                                System.out.println("Message processing aborted due to format errors.");
                            }
                            break;

                        case 2:
                            System.out.println("\nComing Soon.");
                            break;

                        case 3:
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
    
    // Testing hooks for running automated assertions smoothly
    public static void clearData() { globalCount = 0; }
    public static int getGlobalCount() { return globalCount; }
}
