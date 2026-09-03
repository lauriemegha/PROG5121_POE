public class Login {
    private String username;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

    // Constructor
    public Login(String username, String password, String phoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Ensures username contains an underscore and is <= 5 characters long
    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    // Ensures password meets complexity rules
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    // Regex cell phone checker: checks for international code (+) and valid number length
    public boolean checkPhoneNumber() {
        // Matches '+' followed by 1-3 digits country code, followed by 1-10 digits local number
        return phoneNumber != null && phoneNumber.matches("^\\+\\d{1,3}\\d{1,10}$");
    }

    // Returns the registration messaging status based on validation rules
    public String registerUser() {
        if (!checkUserName()) {
            return "Username is not correctly formatted; please ensure that your username"
            +"contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains"
            +"at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkPhoneNumber()) {
            return "Cell number is incorrectly formatted or does not contain an international code"
            +"; please correct the number and try again.";
        }
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    // Verifies that entered details match the registered credentials
    public boolean loginUser(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) && this.password.equals(inputPassword);
    }

    // Returns a customized login status message
    public String returnLoginStatus(boolean loginSuccessful) {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
