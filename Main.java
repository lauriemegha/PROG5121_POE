import java.util.Scanner;
public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;
        String phoneNumber;
        String firstName;
        String lastName;

        System.out.println(" REGISTRATION ");
        
        System.out.println("Please enter your first name: ");
        firstName = scanner.nextLine();

        System.out.println("Please enter your last name: ");
        lastName = scanner.nextLine();

        System.out.println("Please enter your username: ");
        username = scanner.nextLine(); 

        System.out.println("Please enter your password: ");
        password = scanner.nextLine(); 

        System.out.println("Please enter your phone number: ");
        phoneNumber = scanner.nextLine();

        Login user = new Login(username, password, phoneNumber, firstName, lastName);
        String registrationResult = user.registerUser();
        System.out.println("\n---REGISTRATION STATUS---");
        System.out.println(registrationResult);
        
        if(user.checkUserName()&&
            user.checkPasswordComplexity()&&
            user.checkPhoneNumber()){

                System.out.println("LOGIN USER");

                System.out.println("Enter login username: ");
                String inputUsername = scanner.nextLine();

                System.out.println("Enter login password: ");
                String inputPassword = scanner.nextLine();

                boolean isAuthentification = user.loginUser(inputUsername, inputPassword);

                String loginOutput = user.returnLoginStatus(isAuthentification);
                System.out.println("\n---LOGIN STATUS---");
                System.out.println(loginOutput);
            }else{
                System.out.println("\n Registration failed, please try again.");
        }
    }

}
