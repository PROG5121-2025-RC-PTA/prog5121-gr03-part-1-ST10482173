[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/iDYfqQ8M)

import java.util.Scanner;
import java.util.regex.Pattern;

public class ST10482173
{
    private String username;
    private String password;
    private String cellPhoneNumber;

    public static void main(String[] args) {
        ST10482173 login = new ST10482173();
        login.registerUser();
        login.loginUser();
    }

    // Method to register the user
    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        // Input username
        System.out.print("Enter username: ");
        username = scanner.nextLine();
        if (checkUserName(username)){
            System.out.println("Username successfully captured. ");
        } else{
            System.out.println("Username is not correctly formatted; please ensure that your username contains an UNDERSCORE and is no more than 5 CHARACTERS Long. ");
        }

        // Input password
        System.out.print("Enter password: ");
        password = scanner.nextLine();
        if (checkPasswordComplexity(password)){
            System.out.println("Password successfully captured. ");
        } else {
            System.out.println("Password is INCORRECTLY formatted; please ensure that the password contains at least 8 CHARACTERS, a CAPITAL Letter, a NUMBER, and a SPECIAL Character. ");
        }

        // Input cell phone number
        System.out.print("Enter cell phone number: ");
        cellPhoneNumber = scanner.nextLine();
        if (checkCellPhoneNumber(cellPhoneNumber)){
            System.out.println("Phone Number successfully added.");
        } else {
            System.out.println("Cell phone number incorrectly formatted or does not contain INTERNATIONAL CODE.");
        }
    }

    // Method to check username
    public boolean checkUserName(String username) {
        return username.length() <= 5 && username.contains("_");
    }

    // Method to check password complexity
    public boolean checkPasswordComplexity(String password) {
        String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*])[A-Za-z0-9!@#$%^&*]{8,}$";
        return Pattern.matches(regex, password);
    }

    // Method to check cell phone number
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        String regex = "^\\+27[0-9]{9}$"; // South African format with international code
        return Pattern.matches(regex, cellPhoneNumber);
    }

    // Method to login user
    public void loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username to login: ");
        String loginUsername = scanner.nextLine();
        System.out.print("Enter password to login: ");
        String loginPassword = scanner.nextLine();

        if (loginUsername.equals(username) && loginPassword.equals(password)) {
            System.out.println("HELLO " + loginUsername + "IT'S GREAT TO see YOU again.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}
