package com.purerangers;

import java.util.Scanner;

public class InputManager {
    // Initiates the 'Scanner'.
    static Scanner scanner = new Scanner(System.in);
    // Getter for getting a String from the user.
    public static String getString() {
        return scanner.nextLine();
    }
    // Getter for getting an int from the user.
    public static int getInteger()
    {
        return scanner.nextInt();
    }
    // Getter for getting a double from the user
    public static double getDouble(){return scanner.nextDouble();}
    // Method to be called to  handle input exceptions.
    public static void handleInputExceptions(Exception e){
        String message;
        switch (e.toString()){
            case "java.util.InputMismatchException":
                message =  "Please enter your input in the desired format.";
                break;
            case "java.lang.NullPointerException":
                message = "Please ensure you have entered a value before pressing enter.";
                break;
            default:
                message = "Your input was invalid, please review it and try again.";
        };
        DisplayManager.displayMessage(message);
    }
}
