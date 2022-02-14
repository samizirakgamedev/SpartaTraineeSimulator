package com.purerangers;

import java.util.InputMismatchException;

public class DisplayMain {
    public static void main(String[] args) {
        DisplayManager.displayWelcome();
        DisplayManager.displayMessage("Please enter how many months you wish the simulation to run for: ");
        try{
            InputManager.getDouble();
        }catch (Exception e){
            InputManager.handleInputExceptions(e);
        }
        DisplayManager.outputStats(0,0,0,0);
    }
}
