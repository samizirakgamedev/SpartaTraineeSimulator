package com.purerangers;

public class DisplayMain {
    public static void main(String[] args) {
        DisplayManager.displayWelcome();
        DisplayManager.displayMessage("Please enter how many months you wish the simulation to run for: ");
        String userInput = InputManager.getSimulationDuration();
        DisplayManager.displayMessage("Would you like a monthly simulation report?");

        DisplayManager.outputStats(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
    }
}
