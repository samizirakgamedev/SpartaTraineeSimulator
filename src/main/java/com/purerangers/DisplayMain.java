package com.purerangers;

import java.util.Scanner;

public class DisplayMain {
    public static void main(String[] args) {
        DisplayManager.displayWelcome();

        String userInput = "";
        int parsedInput = -1;

        while (parsedInput == -1)
        {
            DisplayManager.displayMessage("How long would you like to run the simulation to for (e.g. 3 months, three years): ");
            userInput = InputManager.getSimulationDuration();
            try
            {
                parsedInput = Integer.parseInt(userInput.replaceAll(" months", ""));
            } catch (Exception e)
            {
                parsedInput = -1;
            }

            DisplayManager.displaySeparator();
        }

        DisplayManager.displayMessage(String.valueOf(parsedInput));
        DisplayManager.displayMessage("You have chosen to run the simulation for "+ userInput);
        DisplayManager.displaySeparator();

        DisplayManager.displayMessage("Would you like a monthly simulation report?");
        Scanner scanner = new Scanner(System.in);
        boolean monthlyProgressReports = scanner.nextBoolean();

        Simulation simulation = new Simulation(monthlyProgressReports, parsedInput);
        simulation.runSimulation();

        //DisplayManager.outputStats(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
    }
}
