package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=============== REGISTRATION ===============");
        System.out.println("Enter First Name: "); String fName = scanner.nextLine();
        System.out.println("Enter Last Name: ");  String lName = scanner.nextLine();
        System.out.println("Enter Username: ");   String uName = scanner.nextLine();
        System.out.println("Enter Password: ");   String pWord = scanner.nextLine();
        System.out.println("Enter Phone Number: "); String phone = scanner.nextLine();

        System.out.println("=============== REGISTRATION RESULTS ===============");
        // Initialize login instance properties
        Login user = new Login(uName, pWord, phone, fName, lName);
        System.out.println("\n" + user.registerUser());
        
        // Cascades forward to the entry flow if validation flags are valid
        if (user.checkUserName() && 
            user.checkPasswordComplexity() && 
            user.checkPhoneNumber()) {
            
            System.out.println("\n=============== LOGIN USER ===============");
            System.out.println("Username: "); String inUser = scanner.nextLine();
            System.out.println("Password: "); String inPass = scanner.nextLine();

            boolean isAuthenticated = user.loginUser(inUser, inPass);
            System.out.println("\n" + user.returnLoginStatus(isAuthenticated));
        } else {
            System.out.println("\nRegistration metrics failed. App terminated.");
        }
        scanner.close();
    }
}
