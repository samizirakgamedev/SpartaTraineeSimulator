package com.purerangers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class UserInputForSimulation {
    private static final Logger logger = LogManager.getLogger("UserInput logger:");

    public static int numOfMonths() {
        int arrSize = 0;
        do {
            System.out.println("Number of months to run the simulation:");
            try {
                Scanner scanner = new Scanner(System.in);
                arrSize = Integer.parseInt(scanner.next());
                logger.info("User has selected: " + arrSize+" Month(s)");
            } catch (Exception e) {
                System.out.println("You must enter a valid number to the corresponding sort.");
                logger.error("Invalid input to the console for choice for array", e);
                arrSize = 0;
            }
        }
        while (arrSize == 0);
        return arrSize;
    }

    // enum for user choice

    public static int simChoice() {
        int numChoice;
        String simchoice = null;
        do {
            System.out.println("Please enter option:");
            int i = 1;
            //list of enum
            for (UserChoice value : UserChoice.values()) {
                System.out.println(i + ": " + value.name());
                i++;
            }
            try {
                Scanner scan = new Scanner(System.in);
                numChoice = Integer.parseInt(scan.next());
                while (numChoice > 3 || numChoice < 0) {
                    System.out.println("Please enter a suitable number:");
                    Scanner a = new Scanner(System.in);
                    numChoice = Integer.parseInt(a.next());
                }
                switch (numChoice) {
                    case 1 -> simchoice = "Print monthly description";
                    case 2 -> simchoice = "Print completed results";
                    case 3 -> System.exit(0);
                }
                logger.info("User has selected to " + simchoice);

            } catch (Exception e) {
                System.out.println("You must enter a valid number to the corresponding sort.");
                logger.error("Invalid input to the console for choice for array", e);
                numChoice = 0;
            }
        }
        while (numChoice == 0);
        return numChoice;
    }
    // enum for user choice
    enum UserChoice {
        PrintMonthly,
        PrintFinalResults,
        Exit
    }
    public static void main(String[] args) {
        numOfMonths();
        simChoice();
    }
}
