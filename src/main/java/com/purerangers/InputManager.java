package com.purerangers;

import java.util.Scanner;

public class InputManager {
    // Initiates the 'Scanner'.
    public static Scanner scanner = new Scanner(System.in);
    // Getter for getting a String from the user.
    public static String getString() {
        try{
            return scanner.nextLine();
        }catch (Exception e){
            handleInputExceptions(e);
            scanner.nextLine();
            getString();
            return null;
        }
    }
    // Getter for getting an int from the user.
    public static int getInteger() {
        try{
            return scanner.nextInt();
        }catch (Exception e){
            handleInputExceptions(e);
            scanner.nextLine();
            getInteger();
            return 0;
        }
    }
    // Getter for getting a double from the user
    public static double getDouble(){
        try{
            return scanner.nextDouble();
        }catch (Exception e){
            handleInputExceptions(e);
            scanner.nextLine();
            getDouble();
            return 0;
        }
    }
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
