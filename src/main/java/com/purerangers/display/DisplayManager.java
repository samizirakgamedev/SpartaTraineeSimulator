package com.purerangers.display;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DisplayManager {
    private static final Logger logger = LogManager.getLogger(DisplayManager.class.getName());
    // Method for outputting a bespoke message to the user.
    public static void displayMessage(String message) {
        logger.info(message);
    }

    // Method for outputting a welcome message to the user.
    public static void displayWelcome() {
        displayMessage(
                new StringBuilder().append("\n======================================================================================\n").append("\n **********************|| Welcome to Sparta Trainee Simulator ||********************** \n").append("\n======================================================================================\n").toString());
    }
    public static void displaySeparator(){
        displayMessage("\n======================================================================================\n");
    }
}
