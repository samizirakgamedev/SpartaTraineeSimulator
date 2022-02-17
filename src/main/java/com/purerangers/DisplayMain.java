package com.purerangers;

public class DisplayMain {
    public static void main(String[] args) {
        DisplayManager.displayWelcome();
        DisplayManager.displayMessage("How long would you like to run the simulation to for (e.g. 3 months, three years): ");
        String userInput = InputManager.getSimulationDuration();
        DisplayManager.displaySeparator();
        DisplayManager.displayMessage("You have chosen to run the simulation for "+ userInput);
        DisplayManager.displaySeparator();
        DisplayManager.displayMessage("Would you like a monthly simulation report?");


        //DisplayManager.outputStats(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
    }
}
