package com.purerangers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        DisplayManager.displayWelcome();

        String userInput = "";
        int parsedMonthsToSimulate = -1;

        while (parsedMonthsToSimulate == -1) {
            DisplayManager.displayMessage("How long would you like to run the simulation to for (e.g. 3 months, three years): ");
            userInput = InputManager.getSimulationDuration();
            try {
                parsedMonthsToSimulate = Integer.parseInt(userInput.replaceAll(" months", ""));
            } catch (Exception e) {
                parsedMonthsToSimulate = -1;
            }

            DisplayManager.displaySeparator();
        }

        DisplayManager.displayMessage(String.valueOf(parsedMonthsToSimulate));
        DisplayManager.displayMessage("You have chosen to run the simulation for " + userInput);
        DisplayManager.displaySeparator();

        DisplayManager.displayMessage("Would you like a monthly simulation report?");
        Scanner scanner = new Scanner(System.in);
        boolean monthlyProgressReports = scanner.nextBoolean();

        Simulation simulation = new Simulation(monthlyProgressReports, parsedMonthsToSimulate);
        simulation.runSimulation();

        try {
            LogfileGenerator.splitLogfile(String.valueOf(parsedMonthsToSimulate));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
